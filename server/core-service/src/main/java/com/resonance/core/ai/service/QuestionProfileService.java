package com.resonance.core.ai.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.springframework.stereotype.Service;

@Service
/**
 * CN: 问题画像只做启发式边界判断，不替代 Agent 检索；它给 Agent 一个更硬的回答契约。
 * EN: Question profiling is heuristic boundary detection, not a replacement for Agent retrieval; it gives the Agent a stricter answer contract.
 * JP: 質問プロファイルはヒューリスティックな境界判断であり Agent 検索の代替ではありません。Agent により強い回答契約を渡します。
 */
public class QuestionProfileService {

    public AnswerMeta profile(String question) {
        String text = normalize(question);
        String productScope = productScope(text);
        String answerType = answerType(text);
        List<String> assumptions = assumptions(text, productScope, answerType);
        List<String> clarifyingQuestions = clarifyingQuestions(text, productScope, answerType);
        return new AnswerMeta(answerType, productScope, "low", assumptions, clarifyingQuestions);
    }

    public AnswerMeta withEvidence(AnswerMeta base, List<SkillContextChunk> chunks, boolean fallback) {
        if (base == null) {
            base = profile("");
        }
        String evidence = fallback ? "fallback" : evidenceLevel(chunks);
        return new AnswerMeta(
                base.answerType(),
                base.productScope(),
                evidence,
                base.assumptions(),
                base.clarifyingQuestions());
    }

    private String answerType(String text) {
        if (containsAny(text, "失败", "报错", "不生效", "无法", "不能", "打不开", "不显示", "不刷新", "问号")) {
            return "troubleshooting";
        }
        if (containsAny(text, "区别", "差别", "不同", "不通用", "对比", "怎么选")) {
            return "product_diff";
        }
        if (containsAny(text, "能使用", "能不能", "可不可以", "有没有")
                && containsAny(text, "函数", "方法", "代码", "脚本", "颜色", "属性", "调用")) {
            return "function_usage";
        }
        if (containsAny(text, "写一个", "写个", "脚本", "代码", "完整例子", "可复制", "查询关系库")) {
            return "code_recipe";
        }
        if (containsAny(text, "函数", "方法", "参数", "怎么用", "能使用", "调用")) {
            return "function_usage";
        }
        if (containsAny(text, "是什么", "是啥", "什么意思", "解释")) {
            return "concept";
        }
        return "unknown";
    }

    private String productScope(String text) {
        if (containsAny(text, "ksp", "portal", "瘦客户端")) {
            return "KSP";
        }
        if (containsAny(text, "kingview", "king view", "组态王")) {
            return "KingView";
        }
        if (containsAny(text, "kinghistorian", "king historian", "工业库", "samplingmode", "calculationmode", "rawbytime", "calculated")) {
            return "KingHistorian";
        }
        if (containsAny(text, "kingscada", "king scada", "scada", "ks", "关系库")) {
            return "KingSCADA";
        }
        return "unclear";
    }

    private List<String> assumptions(String text, String productScope, String answerType) {
        List<String> result = new ArrayList<>();
        if ("KingSCADA".equals(productScope) && containsAny(text, "scada")) {
            result.add("按 KingSCADA 资料判断");
        }
        if ("troubleshooting".equals(answerType) && containsAny(text, "web", "网页", "发布")) {
            result.add("先按传统 Web 发布排查，必要时再分流到 Portal");
        }
        if ("code_recipe".equals(answerType)) {
            result.add("示例中的连接串、控件名和变量名需要按工程替换");
        }
        return result;
    }

    private List<String> clarifyingQuestions(String text, String productScope, String answerType) {
        List<String> result = new ArrayList<>();
        if ("unclear".equals(productScope)) {
            result.add("这个问题对应 KingSCADA、KSP、KingView 还是 KingHistorian？");
        }
        if ("troubleshooting".equals(answerType) && containsAny(text, "web", "网页", "发布")) {
            result.add("你用的是 IIS 传统 Web 发布，还是 KingSCADA-Portal？");
            result.add("失败发生在发布工具、浏览器访问，还是运行后数据刷新？");
        }
        if ("code_recipe".equals(answerType) && containsAny(text, "关系库", "数据库", "sql")) {
            result.add("目标关系库是 SQL Server、MySQL、Oracle 还是 Access？");
        }
        return result.stream().limit(3).toList();
    }

    private String evidenceLevel(List<SkillContextChunk> chunks) {
        if (chunks == null || chunks.isEmpty()) {
            return "low";
        }
        long skillCount = chunks.stream().map(SkillContextChunk::skillName).distinct().count();
        if (chunks.size() >= 6 && skillCount >= 2) {
            return "high";
        }
        return "medium";
    }

    private String normalize(String question) {
        return question == null ? "" : question.toLowerCase(Locale.ROOT);
    }

    private boolean containsAny(String text, String... needles) {
        for (String needle : needles) {
            if (text.contains(needle.toLowerCase(Locale.ROOT))) {
                return true;
            }
        }
        return false;
    }
}
