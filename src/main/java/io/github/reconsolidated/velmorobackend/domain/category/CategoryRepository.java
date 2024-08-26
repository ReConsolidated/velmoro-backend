package io.github.reconsolidated.velmorobackend.domain.category;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByHotelUrlName(String urlName, Sort sort);
}