package com.shary.coreapi.util.mapper;

import com.github.shary2023.docs.model.SubcategorySchema;
import com.shary.coreapi.repository.entity.item.Subcategory;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SubcategoryMapper {

    Subcategory toCategory(SubcategorySchema schema);

    SubcategorySchema toResponse(Subcategory subcategory);

    List<SubcategorySchema> toResponse(List<Subcategory> categories);

}
