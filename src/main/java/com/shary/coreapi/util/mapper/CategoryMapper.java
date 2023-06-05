package com.shary.coreapi.util.mapper;

import com.github.shary2023.docs.model.CategorySchema;
import com.shary.coreapi.repository.entity.item.Category;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {

    Category toCategory(CategorySchema model);

    CategorySchema toResponse(Category category);

    List<CategorySchema> toResponse(List<Category> categories);

}
