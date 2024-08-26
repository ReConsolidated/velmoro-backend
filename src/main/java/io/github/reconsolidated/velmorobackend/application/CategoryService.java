package io.github.reconsolidated.velmorobackend.application;

import io.github.reconsolidated.velmorobackend.domain.category.Category;
import io.github.reconsolidated.velmorobackend.domain.category.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories(String urlName) {
        if (urlName != null) {
            return categoryRepository.findByHotelUrlName(urlName, Sort.by("priority"));
        }
        return categoryRepository.findAll(Sort.by("priority"));
    }

    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Category updateCategory(Long id, Category categoryDetails) {
        if (categoryRepository.existsById(id)) {
            categoryDetails.setId(id);
            return categoryRepository.save(categoryDetails);
        }
        throw new RuntimeException("Category not found");
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}