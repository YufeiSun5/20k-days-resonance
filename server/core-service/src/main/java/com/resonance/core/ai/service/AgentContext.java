package com.resonance.core.ai.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * CN: AgentContext 只保存多轮检索状态，不保存会话归属；用户隔离仍由 AiConversationService 负责。
 * EN: AgentContext stores only multi-round retrieval state; user isolation still belongs to AiConversationService.
 * JP: AgentContext は多段検索状態だけを保持します。ユーザー分離は引き続き AiConversationService が担当します。
 */
class AgentContext {

    private final String question;
    private int round;
    private final List<SearchRoundRecord> searchRounds = new ArrayList<>();
    private final List<SkillContextChunk> collectedChunks = new ArrayList<>();
    private final Set<String> searchedKeys = new HashSet<>();
    private int noNewContextCount;
    private String stopReason = "running";
    private AnswerMeta answerMeta;

    AgentContext(String question, AnswerMeta answerMeta) {
        this.question = question;
        this.answerMeta = answerMeta;
    }

    String question() {
        return question;
    }

    int round() {
        return round;
    }

    void nextRound() {
        round++;
    }

    List<SearchRoundRecord> searchRounds() {
        return searchRounds;
    }

    List<SkillContextChunk> collectedChunks() {
        return collectedChunks;
    }

    Set<String> searchedKeys() {
        return searchedKeys;
    }

    int noNewContextCount() {
        return noNewContextCount;
    }

    void incrementNoNewContextCount() {
        noNewContextCount++;
    }

    void resetNoNewContextCount() {
        noNewContextCount = 0;
    }

    String stopReason() {
        return stopReason;
    }

    void stop(String reason) {
        stopReason = reason;
    }

    AnswerMeta answerMeta() {
        return answerMeta;
    }

    void answerMeta(AnswerMeta answerMeta) {
        this.answerMeta = answerMeta;
    }
}
