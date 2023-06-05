package com.shary.coreapi.controller;

import com.github.shary2023.docs.CategoriesApi;
import com.github.shary2023.docs.model.CategorySchema;
import com.shary.coreapi.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
public class CategoryController implements CategoriesApi {
    private final CategoryService service;

    @Override
    @SneakyThrows
    public ResponseEntity<CategorySchema> createCategory(@RequestBody CategorySchema category) {
        return ResponseEntity.ok(service.create(category));
    }

    @Override
    public ResponseEntity<List<CategorySchema>> getAllCategories() {
        return ResponseEntity.ok(service.getAll());
    }

    @Override
    public ResponseEntity<CategorySchema> getCategoryById(Long categoryId) {
        return ResponseEntity.ok(service.get(categoryId));
    }

    @Override
    @SneakyThrows
    public ResponseEntity<CategorySchema> updateCategory(@RequestBody CategorySchema categorySchema) {
        return ResponseEntity.status(403).body(service.update(categorySchema));
    }

    @Override
    public ResponseEntity<Boolean> deleteCategory(Long categoryId) {
        return ResponseEntity.ok(service.delete());
    }





































//    @Operation(summary = "Create category")
//    @PostMapping
//    public ResponseEntity<CategoryResponse> create(@Valid @RequestBody CategoryModel model)
//            throws MethodArgumentNotValidException {
//        return ResponseEntity.ok(mapper.toResponse(service.create(mapper.toCategory(model)).orElseThrow()));
//    }
//
//    @Operation(summary = "Update category")
//    @PutMapping
//    public ResponseEntity<CategoryResponse> update(@Valid @RequestBody CategoryModel model)
//            throws MethodArgumentNotValidException {
//        return ResponseEntity.ok(mapper.toResponse(service.update(mapper.toCategory(model)).orElseThrow()));
//    }
//
//    @Operation(summary = "Get all categories")
//    @GetMapping
//    public ResponseEntity<List<CategoryResponse>> getAll() {
//        return ResponseEntity.ok(mapper.toResponse(service.getAll()));
//    }
//
//    @Operation(summary = "Delete category")
//    @DeleteMapping("/{categoryId}")
//    public void delete(@PathVariable Long categoryId) {
//        service.delete(categoryId);
//    }


}
