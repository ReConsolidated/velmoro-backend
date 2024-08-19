package io.github.reconsolidated.velmorobackend.domain.hotel;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String displayName;
    private String urlName;
    private String emailAddress;
    private Integer workStartHour;
    private Integer workEndHour;

    public boolean isInWorkingHours() {
        ZonedDateTime nowInWarsaw = ZonedDateTime.now(ZoneId.of("Europe/Warsaw"));
        LocalTime currentTime = nowInWarsaw.toLocalTime();

        LocalTime startTime = LocalTime.of(workStartHour, 0);
        LocalTime endTime = LocalTime.of(workEndHour, 0);

        if (startTime.isBefore(endTime)) {
            return !currentTime.isBefore(startTime) && !currentTime.isAfter(endTime);
        } else {
            return !currentTime.isBefore(startTime) || !currentTime.isAfter(endTime);
        }
    }
}
