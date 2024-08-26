package io.github.reconsolidated.velmorobackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
public class VelmoroBackendApplication {

    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("Europe/Warsaw"));
        SpringApplication.run(VelmoroBackendApplication.class, args);
    }

}
