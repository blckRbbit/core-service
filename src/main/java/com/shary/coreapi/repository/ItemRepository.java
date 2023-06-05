package com.shary.coreapi.repository;

import com.shary.coreapi.repository.entity.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    @Query(value = "SELECT * FROM items WHERE renter_id = :id", nativeQuery = true)
    List<Item> getRentedItemsByUserId(@Param("id") Long renterId);

    @Query(value = "SELECT * FROM items WHERE subcategory_id = :id", nativeQuery = true)
    List<Item> getAllItemsBySubcategoryId(@Param("id") Long subcategoryId);

    @Query(value = "SELECT * FROM items WHERE category_id = :id", nativeQuery = true)
    List<Item> getAllItemsByCategoryId(@Param("id") Long categoryId);

    @Query(value = "SELECT * FROM items WHERE renter_phone = :phone", nativeQuery = true)
    List<Item> getRentedItemsByUserPhone(@Param("phone") String renterPhone);

    @Query(value = "SELECT * FROM items WHERE owner_phone = :phone", nativeQuery = true)
    List<Item> getItemsForRentByUserPhone(@Param("phone") String ownerPhone);

}
