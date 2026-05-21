package com.resonance.core.ai.web;

import com.resonance.core.ai.service.AiConversationService;
import com.resonance.core.ai.service.SkillIndexService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Validated
@RestController
@RequestMapping("/api/core/ai")
/**
 * CN: AI 控制器只暴露对话、历史和补全入口，真实上下文隔离放在 service 层做。
 * EN: The AI controller exposes only chat, history, and completion endpoints. Real context isolation stays in the service layer.
 * JP: AI コントローラーは対話、履歴、補完入口だけを公開します。実際の文脈分離は service 層で行います。
 */
public class AiController {

    private final AiConversationService aiConversationService;
    private final SkillIndexService skillIndexService;

    public AiController(AiConversationService aiConversationService, SkillIndexService skillIndexService) {
        this.aiConversationService = aiConversationService;
        this.skillIndexService = skillIndexService;
    }

    @GetMapping("/skills")
    /**
     * CN: 前端不能写死 skill 名称，新增 data/skills 子目录后由后端索引结果动态返回。
     * EN: The frontend must not hard-code skill names; new data/skills folders are returned from the backend index.
     * JP: フロントで skill 名を固定せず、data/skills の新規ディレクトリはバックエンド索引から動的に返します。
     */
    public List<String> listSkills() {
        return skillIndexService.listSkillNames();
    }

    @PostMapping(value = "/chat/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    /**
     * CN: 流式对话入口，前端靠 delta 事件逐步刷新气泡。
     * EN: Streaming chat entry; the frontend updates the answer bubble incrementally from delta events.
     * JP: ストリーミング対話入口です。フロントは delta イベントで回答バブルを逐次更新します。
     */
    public SseEmitter streamChat(@Valid @RequestBody AiChatStreamRequest request) {
        return aiConversationService.streamChat(request);
    }

    @GetMapping("/conversations/users/{userId}")
    /**
     * CN: 只列出当前 userId 的会话，不提供跨用户扫描能力。
     * EN: List only conversations for the given userId; no cross-user scan is exposed.
     * JP: 指定 userId の会話だけを一覧し、ユーザー横断の走査は公開しません。
     */
    public List<AiConversationResponse> listConversations(@PathVariable UUID userId) {
        return aiConversationService.listConversations(userId);
    }

    @GetMapping("/conversations/{conversationId}/messages")
    /**
     * CN: 读取历史时必须带 userId，service 会校验会话归属。
     * EN: History reads must include userId; the service validates conversation ownership.
     * JP: 履歴読み取りには userId が必須で、service が会話の所有者を検証します。
     */
    public List<AiMessageResponse> listMessages(
            @PathVariable UUID conversationId,
            @RequestParam UUID userId) {
        return aiConversationService.listMessages(conversationId, userId);
    }

    @PostMapping("/prefix-completions")
    /**
     * CN: 补全接口第一版走短文本非流式，避免把输入框体验和主回答流混在一起。
     * EN: Prefix completion v1 uses short non-streaming responses so input-box UX is not mixed with the main answer stream.
     * JP: 補完 API の初版は短い非ストリーミング応答にし、入力欄 UX と主回答ストリームを混ぜません。
     */
    public AiPrefixCompletionResponse completePrefix(@Valid @RequestBody AiPrefixCompletionRequest request) {
        return aiConversationService.completePrefix(request);
    }
}
