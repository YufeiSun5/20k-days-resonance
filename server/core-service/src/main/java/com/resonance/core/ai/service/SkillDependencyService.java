package com.resonance.core.ai.service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import org.springframework.stereotype.Service;

@Service
/**
 * CN: skill 依赖扩展用规则表处理组合任务，例如“报表里查 SQL”同时看报表、数据库和命令语言。
 * EN: Skill dependency expansion uses a rule table for combined tasks, for example SQL inside reports needs report, database, and scripting skills together.
 * JP: skill 依存展開はルール表で複合タスクを扱います。たとえば帳票内 SQL は帳票、DB、命令言語 skill を同時に参照します。
 */
public class SkillDependencyService {

    private static final int MAX_EXPANDED_SKILLS = 5;

    private static final List<DependencyRule> RULES = List.of(
            new DependencyRule("kingview-reports-recipes", List.of("kingview-scripting"), List.of()),
            new DependencyRule("kingview-reports-recipes", List.of("kingview-database"), List.of("sql", "数据库", "查询", "odbc", "sqlselect", "sqlconnect", "数据源")),
            new DependencyRule("kingview-database", List.of("kingview-scripting"), List.of()),
            new DependencyRule("kingview-graphics", List.of("kingview-scripting", "kingview-variables"), List.of("控件", "选择器", "日历", "datepicker", "datetimepicker", "activex", "属性", "方法", "值")),
            new DependencyRule("kingview-curves-alarm", List.of("kingview-database", "kingview-scripting"), List.of("历史", "数据库", "查询", "sql", "odbc", "历史库")),
            new DependencyRule("kingscada-curves-charts", List.of("kingscada-dataset", "kingscada-kh", "kingscada-scripting"), List.of("历史", "数据库", "查询", "sql", "kdb", "kh")),
            new DependencyRule("kingscada-kh", List.of("kingscada-dataset"), List.of()),
            new DependencyRule("kingscada-dataset", List.of("kingscada-functions"), List.of()),
            new DependencyRule("kingscada-ch05-controls", List.of("kingscada-ch11-scripting", "kingscada-ch09-variables"), List.of()),
            new DependencyRule("kingscada-ch12-alarm-event", List.of("kingscada-ch09-variables", "kingscada-ch11-scripting"), List.of()),
            new DependencyRule("kingscada-ch12-alarm-event", List.of("kingscada-ch18-database"), List.of("历史", "记录", "查询", "数据库", "sql")),
            new DependencyRule("kingscada-ch14-curves-charts", List.of("kingscada-ch11-scripting", "kingscada-command-functions"), List.of()),
            new DependencyRule("kingscada-ch14-curves-charts", List.of("kingscada-ch23-history", "kingscada-kh"), List.of("历史", "查询", "kh", "kdb")),
            new DependencyRule("kingscada-ch15-reports", List.of("kingscada-ch11-scripting", "kingscada-command-functions"), List.of()),
            new DependencyRule("kingscada-ch15-reports", List.of("kingscada-ch18-database"), List.of("sql", "数据库", "查询", "odbc")),
            new DependencyRule("kingscada-ch18-database", List.of("kingscada-ch11-scripting", "kingscada-command-functions"), List.of()),
            new DependencyRule("kingscada-ch23-history", List.of("kingscada-kh", "kingscada-dataset"), List.of("kh", "kdb", "查询", "sql", "工业库")));

    public List<String> expand(String query, List<String> routedSkillNames, List<String> availableSkillNames) {
        Set<String> available = normalizeSet(availableSkillNames);
        LinkedHashSet<String> expanded = normalizeSet(routedSkillNames);
        expanded.retainAll(available);

        String text = query == null ? "" : query.toLowerCase(Locale.ROOT);
        for (DependencyRule rule : RULES) {
            if (expanded.contains(rule.primarySkill()) && rule.matches(text)) {
                rule.dependentSkills().stream()
                        .filter(available::contains)
                        .forEach(expanded::add);
            }
        }

        return expanded.stream().limit(MAX_EXPANDED_SKILLS).toList();
    }

    private LinkedHashSet<String> normalizeSet(List<String> values) {
        LinkedHashSet<String> result = new LinkedHashSet<>();
        if (values == null) {
            return result;
        }
        values.stream()
                .filter(value -> value != null && !value.isBlank())
                .map(value -> value.trim().toLowerCase(Locale.ROOT))
                .forEach(result::add);
        return result;
    }

    private record DependencyRule(String primarySkill, List<String> dependentSkills, List<String> triggers) {
        boolean matches(String text) {
            return triggers.isEmpty() || triggers.stream().anyMatch(text::contains);
        }
    }
}
