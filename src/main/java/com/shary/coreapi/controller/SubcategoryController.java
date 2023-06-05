package com.shary.coreapi.controller;

import com.github.shary2023.docs.SubcategoriesApi;
import com.github.shary2023.docs.model.SubcategorySchema;
import com.shary.coreapi.service.SubcategoryService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
public class SubcategoryController implements SubcategoriesApi {
    private final SubcategoryService service;

    @Override
    @SneakyThrows
    @Transactional
    public ResponseEntity<SubcategorySchema> createSubcategory(@RequestBody SubcategorySchema subcategorySchema) {
        return ResponseEntity.ok(service.createSubcategory(subcategorySchema));
    }

    @Override
    public ResponseEntity<SubcategorySchema> getSubcategoryById(@PathVariable Long subcategoryId) {
        return ResponseEntity.ok(service.getSubcategoryById(subcategoryId));
    }

    @Override
    public ResponseEntity<List<SubcategorySchema>> getAllSubcategories() {
        return ResponseEntity.ok(service.getAllSubcategories());
    }

    @Override
    public ResponseEntity<List<SubcategorySchema>> getAllSubcategoriesForCategory(@PathVariable Long categoryId) {
        return ResponseEntity.ok(service.getAllSubcategoriesForCategory(categoryId));
    }

    @Override
    @SneakyThrows
    public ResponseEntity<SubcategorySchema> updateSubcategory(@RequestBody SubcategorySchema update) {
        return ResponseEntity.ok(service.updateSubcategory(update));
    }

    @Override
    public ResponseEntity<Boolean> deleteSubcategory(Long subcategoryId) {
        return ResponseEntity.ok(service.delete());
    }
}
