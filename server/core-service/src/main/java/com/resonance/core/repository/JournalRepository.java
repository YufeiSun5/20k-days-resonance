package com.resonance.core.repository;

import com.resonance.core.entity.Journal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JournalRepository extends JpaRepository<Journal, UUID> {

    // CN: 星期几命中是当前空间共振推荐的第一优先级查询。
    // EN: Weekday hit is the first-priority query for the current spatial resonance recommendation path.
    // JP: 曜日一致は、現在の空間共鳴推薦経路で最優先の問い合わせです。
    Optional<Journal> findFirstByUserIdAndOriginalWeekdayAndDeletedFalseOrderByCreatedAtDesc(UUID userId, short originalWeekday);

    // CN: 如果 weekday 没命中，就退化成用户最近一条日记。
    // EN: If weekday lookup misses, degrade to the user's latest journal.
    // JP: weekday 参照が外れた場合は、そのユーザーの最新日記にフォールバックします。
    Optional<Journal> findFirstByUserIdAndDeletedFalseOrderByCreatedAtDesc(UUID userId);

    List<Journal> findTop20ByUserIdAndDeletedFalseOrderByCreatedAtDesc(UUID userId);
}