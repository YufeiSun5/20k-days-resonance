package com.resonance.core.ai.repository;

import com.resonance.core.ai.entity.AiMessage;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AiMessageRepository extends JpaRepository<AiMessage, UUID> {

    // CN: 历史拼接只拿最近消息，控制请求长度和成本。
    // EN: History stitching uses only recent messages to control request size and cost.
    // JP: 履歴連結では直近メッセージだけを使い、要求サイズとコストを抑えます。
    List<AiMessage> findByConversationIdAndUserIdOrderByCreatedAtDesc(UUID conversationId, UUID userId, Pageable pageable);

    List<AiMessage> findByConversationIdAndUserIdOrderByCreatedAtAsc(UUID conversationId, UUID userId);
}
