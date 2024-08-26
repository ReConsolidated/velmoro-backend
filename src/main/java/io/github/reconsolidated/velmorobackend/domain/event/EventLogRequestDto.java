package io.github.reconsolidated.velmorobackend.domain.event;

import com.fasterxml.jackson.annotation.JsonProperty;

public record EventLogRequestDto(@JsonProperty("userId") String userId,
                              @JsonProperty("eventType") String eventType,
                              @JsonProperty("details") String details) {
}