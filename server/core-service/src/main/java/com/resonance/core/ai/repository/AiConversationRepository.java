package com.resonance.core.ai.repository;

import com.resonance.core.ai.entity.AiConversation;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AiConversationRepository extends JpaRepository<AiConversation, UUID> {

    // CN: 会话列表必须按 userId 隔离，不能让一个用户扫到另一个用户的历史。
    // EN: Conversation lists must be isolated by userId so one user cannot scan another user's history.
    // JP: 会話一覧は userId で分離し、あるユーザーが別ユーザーの履歴を読めないようにします。
    List<AiConversation> findTop20ByUserIdOrderByUpdatedAtDesc(UUID userId);

    Optional<AiConversation> findByIdAndUserId(UUID id, UUID userId);
}
