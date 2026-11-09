package com.zeeshan.financeapi.category;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class CategoryService {

    // TEMPORARY until Day 6: every request acts as the test user (id 1).
    private static final Long CURRENT_USER_ID = 1L;

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public CategoryResponse create(CreateCategoryRequest request) {
        if (categoryRepository.existsByUserIdAndName(CURRENT_USER_ID, request.name())) {
            throw new DuplicateCategoryException();
        }
        Category category = new Category(CURRENT_USER_ID, request.name(), request.kind());
        return CategoryResponse.from(categoryRepository.save(category));
    }

    @Transactional(readOnly = true)
    public List<CategoryResponse> list() {
        return categoryRepository.findByUserId(CURRENT_USER_ID).stream()
                .map(CategoryResponse::from)
                .toList();
    }
}