package io.github.reconsolidated.velmorobackend.domain.hotel;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class NotInWorkingHoursException extends RuntimeException {

    public NotInWorkingHoursException() {
        super("Operation not allowed outside of working hours.");
    }

    public NotInWorkingHoursException(String message) {
        super(message);
    }
}
