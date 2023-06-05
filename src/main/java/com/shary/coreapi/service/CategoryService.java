package com.shary.coreapi.service;

import com.github.shary2023.docs.model.CategorySchema;
import com.shary.coreapi.repository.CategoryRepository;
import com.shary.coreapi.util.exception.ResourceNotFoundException;
import com.shary.coreapi.util.exception.UpdateRequestInsufficientRightsException;
import com.shary.coreapi.util.mapper.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository repository;
    private final CategoryMapper mapper;

    @Value("${app.messages.errors.category.by-id-not-found}")
    private String categoryByIdNotFoundError;

    @Transactional
    public CategorySchema create(@Valid CategorySchema category) throws MethodArgumentNotValidException {
        return Optional.of(mapper.toResponse(repository.save(mapper.toCategory(category)))).orElseThrow();
    }

    public List<CategorySchema> getAll() {
        return mapper.toResponse(repository.findAll());
    }

    public CategorySchema get(Long categoryId) {
        return mapper.toResponse(
                repository.findById(categoryId)
                        .orElseThrow(
                                () -> new ResourceNotFoundException(
                                        String.format(categoryByIdNotFoundError, categoryId))
                        )
        );
    }

    @Transactional
    public CategorySchema update(@Valid CategorySchema update) throws MethodArgumentNotValidException {
        throw new UpdateRequestInsufficientRightsException("Entity has not been modified: Insufficient rights");
    }

    public boolean delete() {
        throw new UpdateRequestInsufficientRightsException("Entity has not been modified: Insufficient rights");
    }
}
