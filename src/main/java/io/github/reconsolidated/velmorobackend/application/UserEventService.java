package io.github.reconsolidated.velmorobackend.application;

import io.github.reconsolidated.velmorobackend.domain.event.UserEvent;
import io.github.reconsolidated.velmorobackend.domain.event.UserEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserEventService {

    @Autowired
    private UserEventRepository userEventRepository;

    public UserEvent logEvent(String userId, String eventType, String details) {
        UserEvent event = new UserEvent();
        event.setUserId(userId);
        event.setEventType(eventType);
        event.setEventTimestamp(LocalDateTime.now());
        event.setDetails(details);
        return userEventRepository.save(event);
    }

    public List<UserEvent> getUserEvents(String userId) {
        return userEventRepository.findByUserIdOrderByEventTimestamp(userId);
    }

    public List<UserEvent> getAllEvents() {
        return userEventRepository.findAll(Sort.by("eventTimestamp"));
    }

    public List<UserEvent> getAllHotelEvents(String urlName) {
        return userEventRepository.findByUrlNameOrderByEventTimestamp(urlName);
    }
}