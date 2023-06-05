package com.shary.coreapi.controller;

import com.github.shary2023.docs.ItemsApi;
import com.github.shary2023.docs.model.ItemSchema;
import com.shary.coreapi.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@RequiredArgsConstructor
public class ItemController implements ItemsApi {
    private final ItemService service;

    @Override
    @SneakyThrows
    @Transactional
    public ResponseEntity<ItemSchema> createItem(@Valid @RequestBody ItemSchema itemSchema) {
        return ResponseEntity.ok(service.createPreparedForRentItem(itemSchema));
    }

    @Override
    @SneakyThrows
    //todo rest-api переделать параметр id на телефон
    public ResponseEntity<ItemSchema> rentAnItem(Long itemId, @NotNull @Valid Long renterId) {
//        return ResponseEntity.ok(service.rentAnItem(itemId, renterId));
        return null;
    }

    @Override
    @SneakyThrows
    @Transactional
    public ResponseEntity<Boolean> returnRentedItem(Long itemId) {
        return ResponseEntity.ok(service.returnRentedItem(itemId));
    }

    @Override
    public ResponseEntity<List<ItemSchema>> getAllItems() {
        return ResponseEntity.ok(service.getAllItems());
    }

    @Override
    public ResponseEntity<List<ItemSchema>> getAllItemsForCategory(Long categoryId) {
        return ResponseEntity.ok(service.getAllItemsForCategory(categoryId));
    }

    @Override
    public ResponseEntity<List<ItemSchema>> getAllItemsForSubcategory(Long subcategoryId) {
        return ResponseEntity.ok(service.getAllItemsForSubcategory(subcategoryId));
    }

    @Override
    public ResponseEntity<List<ItemSchema>> getRentedItemsForUserByUserPhone(String phone) {
        return ResponseEntity.ok(service.getRentedItemsForUserByUserPhone(phone));
    }

    @Override
    public ResponseEntity<List<ItemSchema>> getItemsRentedForUserByUserPhone(String phone) {
        return ResponseEntity.ok(service.getItemsRentedForUserByUserId(phone));
    }

    @Override
    public ResponseEntity<ItemSchema> getItemById(Long itemId) {
        return ResponseEntity.ok(service.getItemById(itemId));
    }

    @Override
    @SneakyThrows
    @Transactional
    public ResponseEntity<ItemSchema> updateItem(@Valid ItemSchema itemSchema) {
        return ResponseEntity.ok(service.updateItem(itemSchema));
    }

    @Override
    @Transactional
    public ResponseEntity<Boolean> deleteItem(Long itemId) {
        return ResponseEntity.ok(service.delete(itemId));
    }
}
