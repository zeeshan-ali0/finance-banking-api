package com.zeeshan.financeapi.category;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryResponse create(@Valid @RequestBody CreateCategoryRequest request) {
        return categoryService.create(request);
    }

    @GetMapping
    public List<CategoryResponse> list() {
        return categoryService.list();
    }
}