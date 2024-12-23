package io.github.reconsolidated.velmorobackend.domain.hotel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {
    Optional<Hotel> findByUrlName(String name);
}
