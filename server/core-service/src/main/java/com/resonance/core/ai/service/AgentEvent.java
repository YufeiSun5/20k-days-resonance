package com.resonance.core.ai.service;

/**
 * CN: AgentEvent 是后端向 SSE 层透出的检索过程事件，不参与数据库持久化。
 * EN: AgentEvent exposes retrieval trace events to the SSE layer and is not persisted.
 * JP: AgentEvent は検索過程イベントを SSE 層へ渡すだけで、DB には保存しません。
 */
public record AgentEvent(
        String name,
        Object data) {
}
