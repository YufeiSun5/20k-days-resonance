package com.resonance.core.ai.service;

import com.resonance.core.ai.AiProperties;
import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Stream;
import org.springframework.stereotype.Service;

@Service
/**
 * CN: skill 检索第一版用本地 Markdown 分块，不引入向量索引，先保证部署后能稳定回答已有知识库问题。
 * EN: Skill retrieval v1 uses local Markdown chunks instead of vector indexing so deployed builds can reliably answer from the existing knowledge base first.
 * JP: skill 検索の初版はベクトル索引ではなくローカル Markdown 分割を使い、まず既存ナレッジベースから安定して回答できるようにします。
 */
public class SkillIndexService {

    private static final int MAX_CHUNK_CHARS = 2600;

    private final AiProperties properties;
    private volatile List<SkillChunk> chunks = List.of();

    public SkillIndexService(AiProperties properties) {
        this.properties = properties;
    }

    @PostConstruct
    void loadOnStartup() {
        reload();
    }

    public synchronized void reload() {
        Path root = resolveRoot();
        if (root == null) {
            chunks = List.of();
            return;
        }

        List<SkillChunk> loaded = new ArrayList<>();
        try (Stream<Path> files = Files.walk(root)) {
            files.filter(Files::isRegularFile)
                    .filter(path -> path.getFileName().toString().toLowerCase(Locale.ROOT).endsWith(".md"))
                    .sorted()
                    .forEach(path -> loaded.addAll(readChunks(root, path)));
        } catch (IOException ignored) {
            loaded.clear();
        }
        List<SkillChunk> indexed = new ArrayList<>();
        for (int i = 0; i < loaded.size(); i++) {
            indexed.add(loaded.get(i).withIndex(i));
        }
        chunks = List.copyOf(indexed);
    }

    public List<SkillSource> search(String query, List<String> skillNames, int limit) {
        if (query == null || query.isBlank() || chunks.isEmpty()) {
            return List.of();
        }

        Set<String> allowedSkills = new HashSet<>();
        if (skillNames != null) {
            skillNames.stream()
                    .filter(value -> value != null && !value.isBlank())
                    .map(value -> value.trim().toLowerCase(Locale.ROOT))
                    .forEach(allowedSkills::add);
        }
        Set<String> mentionedSkills = mentionedSkillNames(query);
        if (!mentionedSkills.isEmpty()) {
            if (allowedSkills.isEmpty()) {
                allowedSkills.addAll(mentionedSkills);
            } else {
                allowedSkills.retainAll(mentionedSkills);
            }
        }
        Set<String> terms = tokenize(query);

        return chunks.stream()
                .filter(chunk -> allowedSkills.isEmpty() || allowedSkills.contains(chunk.skillName().toLowerCase(Locale.ROOT)))
                .map(chunk -> new ScoredChunk(chunk, score(chunk, terms)))
                .filter(scored -> scored.score() > 0)
                .sorted(Comparator.comparingInt(ScoredChunk::score).reversed())
                .limit(limit)
                .map(scored -> scored.chunk().toSource(terms))
                .toList();
    }

    public SkillContextResult searchContext(SkillSearchRequest request, String userQuestion, int limit) {
        if (request == null || request.skill() == null || request.skill().isBlank() || chunks.isEmpty()) {
            return new SkillContextResult(request, List.of(), true);
        }

        String skillName = request.skill().trim().toLowerCase(Locale.ROOT);
        Set<String> terms = tokenize(userQuestion == null ? "" : userQuestion);
        addTerms(terms, request.keywords());
        addTerms(terms, request.headings());

        List<ScoredChunk> matches = chunks.stream()
                .filter(chunk -> skillName.equals(chunk.skillName().toLowerCase(Locale.ROOT)))
                .map(chunk -> new ScoredChunk(chunk, scoreForRequest(chunk, terms, request)))
                .filter(scored -> scored.score() > 0)
                .sorted(Comparator.comparingInt(ScoredChunk::score).reversed())
                .limit(Math.max(1, limit))
                .toList();

        LinkedHashMap<String, SkillContextChunk> expanded = new LinkedHashMap<>();
        for (ScoredChunk scored : matches) {
            addExpandedChunk(expanded, scored, terms);
            neighbor(scored.chunk(), -1).ifPresent(chunk -> addExpandedChunk(expanded, new ScoredChunk(chunk, Math.max(1, scored.score() - 4)), terms));
            neighbor(scored.chunk(), 1).ifPresent(chunk -> addExpandedChunk(expanded, new ScoredChunk(chunk, Math.max(1, scored.score() - 4)), terms));
        }

        return new SkillContextResult(request, List.copyOf(expanded.values()), expanded.isEmpty());
    }

    public List<String> listSkillNames() {
        return chunks.stream()
                .map(SkillChunk::skillName)
                .filter(skillName -> !"skills".equals(skillName))
                .distinct()
                .sorted()
                .toList();
    }

    public String guideExcerpt(int maxChars) {
        Path root = resolveRoot();
        if (root == null) {
            return "";
        }
        Path guide = root.resolve("skills_index.md");
        if (!Files.isRegularFile(guide)) {
            return "";
        }
        try {
            String content = Files.readString(guide, StandardCharsets.UTF_8);
            if (content.length() <= maxChars) {
                return content;
            }
            return content.substring(0, Math.max(0, maxChars)) + "\n\n...(guide truncated)...";
        } catch (IOException ignored) {
            return "";
        }
    }

    private Set<String> mentionedSkillNames(String query) {
        String lowered = query.toLowerCase(Locale.ROOT);
        Set<String> result = new HashSet<>();
        for (String skillName : listSkillNames()) {
            if (lowered.contains(skillName.toLowerCase(Locale.ROOT))) {
                result.add(skillName.toLowerCase(Locale.ROOT));
            }
        }
        return result;
    }

    public String buildContext(List<SkillSource> sources) {
        if (sources == null || sources.isEmpty()) {
            return "No matching skill excerpt was found.";
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < sources.size(); i++) {
            SkillSource source = sources.get(i);
            builder.append("Skill source ").append(i + 1).append('\n')
                    .append("skill: ").append(source.skillName()).append('\n')
                    .append("path: ").append(source.path()).append('\n')
                    .append("heading: ").append(source.heading()).append('\n')
                    .append(source.excerpt()).append("\n\n");
        }
        return builder.toString();
    }

    private Path resolveRoot() {
        List<Path> candidates = List.of(
                Path.of(properties.getSkillsRoot()),
                Path.of("").toAbsolutePath().resolve(properties.getSkillsRoot()),
                Path.of("").toAbsolutePath().resolve("data/skills"),
                Path.of("").toAbsolutePath().resolve("../../data/skills"));

        for (Path candidate : candidates) {
            Path normalized = candidate.normalize();
            if (Files.isDirectory(normalized)) {
                return normalized;
            }
        }
        return null;
    }

    private List<SkillChunk> readChunks(Path root, Path path) {
        try {
            String content = Files.readString(path, StandardCharsets.UTF_8);
            String relativePath = root.relativize(path).toString().replace('\\', '/');
            String skillName = resolveSkillName(relativePath);
            return splitMarkdown(skillName, relativePath, content);
        } catch (IOException ignored) {
            return List.of();
        }
    }

    private String resolveSkillName(String relativePath) {
        int slash = relativePath.indexOf('/');
        if (slash > 0) {
            return relativePath.substring(0, slash);
        }
        return "skills";
    }

    private List<SkillChunk> splitMarkdown(String skillName, String path, String content) {
        List<SkillChunk> result = new ArrayList<>();
        String heading = "document";
        StringBuilder current = new StringBuilder();

        for (String line : content.split("\\R")) {
            if (line.startsWith("#") && current.length() > 0) {
                addChunk(result, skillName, path, heading, current.toString());
                current.setLength(0);
                heading = line.replaceFirst("^#+", "").trim();
            } else if (line.startsWith("#")) {
                heading = line.replaceFirst("^#+", "").trim();
            }
            current.append(line).append('\n');
            if (current.length() >= MAX_CHUNK_CHARS) {
                addChunk(result, skillName, path, heading, current.toString());
                current.setLength(0);
            }
        }
        addChunk(result, skillName, path, heading, current.toString());
        return result;
    }

    private void addChunk(List<SkillChunk> result, String skillName, String path, String heading, String content) {
        if (content == null || content.isBlank()) {
            return;
        }
        result.add(new SkillChunk(result.size(), skillName, path, heading == null || heading.isBlank() ? "document" : heading, content.trim()));
    }

    private Set<String> tokenize(String query) {
        Set<String> terms = new HashSet<>();
        String loweredQuery = query.toLowerCase(Locale.ROOT);
        for (String term : loweredQuery.split("[\\s,，。.!?？;；:：()（）\\[\\]{}<>\"'`]+")) {
            if (term.length() >= 2) {
                terms.add(term);
            }
        }
        addQueryAliases(loweredQuery, terms);
        for (int i = 0; i + 2 <= query.length(); i++) {
            terms.add(query.substring(i, i + 2).toLowerCase(Locale.ROOT));
        }
        return terms;
    }

    private void addQueryAliases(String query, Set<String> terms) {
        if (containsAny(query, "选择器", "日期控件", "时间控件", "日期时间", "datepicker", "datetimepicker")
                && containsAny(query, "日期", "时间", "日历", "date", "time")) {
            terms.add("日历控件");
            terms.add("日期控件");
            terms.add("时间控件");
            terms.add("datetimectrl");
            terms.add("dattimctrl");
            terms.add("microsoft date and time picker");
            terms.add("控件属性和方法");
            terms.add("getyear");
            terms.add("selchangedata");
        }
    }

    private int score(SkillChunk chunk, Set<String> terms) {
        String heading = chunk.heading().toLowerCase(Locale.ROOT);
        String content = chunk.content().toLowerCase(Locale.ROOT);
        int score = chunk.path().contains("SKILL.md") ? 5 : 0;
        score += chunk.path().contains("/references/") ? 8 : 0;
        score -= chunk.path().contains("路引") ? 8 : 0;
        score -= "skills".equals(chunk.skillName()) ? 8 : 0;
        for (String term : terms) {
            if (heading.contains(term)) {
                score += term.length() >= 4 ? 12 : 6;
            }
            if (content.contains(term)) {
                score += term.length() >= 4 ? 8 : 2;
            }
        }
        return score;
    }

    private void addTerms(Set<String> terms, List<String> values) {
        if (values == null) {
            return;
        }
        values.stream()
                .filter(value -> value != null && !value.isBlank())
                .map(value -> value.toLowerCase(Locale.ROOT))
                .forEach(value -> {
                    terms.add(value);
                    for (String term : value.split("[\\s,，。.!?？;；:：()（）\\[\\]{}<>\"'`]+")) {
                        if (term.length() >= 2) {
                            terms.add(term);
                        }
                    }
                });
    }

    private int scoreForRequest(SkillChunk chunk, Set<String> terms, SkillSearchRequest request) {
        String heading = chunk.heading().toLowerCase(Locale.ROOT);
        String content = chunk.content().toLowerCase(Locale.ROOT);
        int score = 0;
        for (String term : terms) {
            if (heading.contains(term)) {
                score += term.length() >= 4 ? 14 : 7;
            }
            if (content.contains(term)) {
                score += term.length() >= 4 ? 8 : 2;
            }
        }
        if (request.headings() != null) {
            for (String requestedHeading : request.headings()) {
                if (requestedHeading != null && !requestedHeading.isBlank() && heading.contains(requestedHeading.toLowerCase(Locale.ROOT))) {
                    score += 30;
                }
            }
        }
        if (request.keywords() != null) {
            for (String keyword : request.keywords()) {
                if (keyword == null || keyword.isBlank()) {
                    continue;
                }
                String lowered = keyword.toLowerCase(Locale.ROOT);
                if (heading.contains(lowered)) {
                    score += 20;
                }
                if (content.contains(lowered)) {
                    score += 10;
                }
            }
        }
        if (score > 0 && chunk.path().contains("/references/")) {
            score += 4;
        }
        return score;
    }

    private java.util.Optional<SkillChunk> neighbor(SkillChunk chunk, int offset) {
        int targetIndex = chunk.index() + offset;
        if (targetIndex < 0 || targetIndex >= chunks.size()) {
            return java.util.Optional.empty();
        }
        SkillChunk candidate = chunks.get(targetIndex);
        if (candidate.skillName().equals(chunk.skillName()) && candidate.path().equals(chunk.path())) {
            return java.util.Optional.of(candidate);
        }
        return java.util.Optional.empty();
    }

    private void addExpandedChunk(LinkedHashMap<String, SkillContextChunk> expanded, ScoredChunk scored, Set<String> terms) {
        SkillContextChunk chunk = scored.chunk().toContextChunk(terms, scored.score());
        expanded.putIfAbsent(chunk.skillName() + "::" + chunk.path() + "::" + chunk.heading(), chunk);
    }

    private boolean containsAny(String text, String... needles) {
        for (String needle : needles) {
            if (text.contains(needle)) {
                return true;
            }
        }
        return false;
    }

    private record ScoredChunk(SkillChunk chunk, int score) {
    }
}
