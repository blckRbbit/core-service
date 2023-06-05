package com.shary.coreapi.service;

import com.github.shary2023.docs.model.SubcategorySchema;
import com.shary.coreapi.repository.SubcategoryRepository;
import com.shary.coreapi.util.exception.ResourceNotFoundException;
import com.shary.coreapi.util.exception.UpdateRequestInsufficientRightsException;
import com.shary.coreapi.util.mapper.SubcategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.validation.Valid;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubcategoryService {
    private final SubcategoryRepository repository;
    private final SubcategoryMapper subcategoryMapper;

    @Value("${app.messages.errors.subcategory.by-id-not-found}")
    private String subcategoryByIdNotFoundError;

    @Transactional
    public SubcategorySchema createSubcategory(@Valid SubcategorySchema subcategory)
            throws MethodArgumentNotValidException {
        throw new UpdateRequestInsufficientRightsException("Entity has not been modified: Insufficient rights");
    }

    public SubcategorySchema getSubcategoryById(Long subcategoryId) {
        return subcategoryMapper.toResponse(repository.findById(subcategoryId).orElseThrow(
                () -> new ResourceNotFoundException(String.format(subcategoryByIdNotFoundError, subcategoryId))));
    }

    public List<SubcategorySchema> getAllSubcategories() {
        return subcategoryMapper.toResponse(repository.findAll());
    }

    public List<SubcategorySchema> getAllSubcategoriesForCategory(Long categoryId) {
        return subcategoryMapper.toResponse(repository.getByCategoryId(categoryId));
    }

    public SubcategorySchema updateSubcategory(@Valid SubcategorySchema update) throws MethodArgumentNotValidException {
        throw new UpdateRequestInsufficientRightsException("Entity has not been modified: Insufficient rights");
    }

    public boolean delete() {
        throw new UpdateRequestInsufficientRightsException("Entity has not been modified: Insufficient rights");
    }

}

