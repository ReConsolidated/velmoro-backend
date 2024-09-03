package io.github.reconsolidated.velmorobackend.infrastracture.rest;

import io.github.reconsolidated.velmorobackend.application.UserEventService;
import io.github.reconsolidated.velmorobackend.domain.event.EventLogRequestDto;
import io.github.reconsolidated.velmorobackend.domain.event.UserEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class UserEventController {

    @Autowired
    private UserEventService userEventService;

    @PostMapping("/log")
    public UserEvent logEvent(@RequestBody EventLogRequestDto request) {
        return userEventService.logEvent(request.userId(), request.eventType(), request.details());
    }

    @GetMapping("/user/{userId}")
    public List<UserEvent> getUserEvents(@PathVariable String userId) {
        return userEventService.getUserEvents(userId);
    }

    @GetMapping
    public List<UserEvent> getAllEvents() {
        return userEventService.getAllEvents();
    }

    @GetMapping("/hotel/{urlName}")
    public List<UserEvent> getHotelEvents(@PathVariable String urlName) {
        return userEventService.getAllHotelEvents(urlName);
    }
}
