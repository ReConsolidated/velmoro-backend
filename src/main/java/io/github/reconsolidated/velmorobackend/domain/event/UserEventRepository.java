package io.github.reconsolidated.velmorobackend.domain.event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserEventRepository extends JpaRepository<UserEvent, Long> {

    List<UserEvent> findByUserId(String userId);

    List<UserEvent> findByUserIdOrderByEventTimestamp(String userId);

    List<UserEvent> findByUrlNameOrderByEventTimestamp(String urlName);
}