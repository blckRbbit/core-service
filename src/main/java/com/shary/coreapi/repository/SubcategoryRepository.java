package com.shary.coreapi.repository;

import com.shary.coreapi.repository.entity.item.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubcategoryRepository extends JpaRepository<Subcategory, Long> {
    List<Subcategory> getByCategoryId(Long categoryId);
}
