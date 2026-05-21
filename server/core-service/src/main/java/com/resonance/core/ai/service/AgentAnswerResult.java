package com.resonance.core.ai.service;

import java.util.List;

/**
 * CN: AgentAnswerResult 是一次 AI 回答的业务结果，包含最终文本、引用片段和检索停止原因。
 * EN: AgentAnswerResult is the business result for one AI answer, including final text, sources, and the retrieval stop reason.
 * JP: AgentAnswerResult は 1 回の AI 回答結果で、最終テキスト、引用断片、検索停止理由を含みます。
 */
public record AgentAnswerResult(
        String answer,
        List<SkillSource> sources,
        List<String> usedSkills,
        int roundCount,
        String stopReason,
        boolean fallback,
        AnswerMeta answerMeta) {
}
