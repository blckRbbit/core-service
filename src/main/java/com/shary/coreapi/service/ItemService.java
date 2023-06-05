package com.shary.coreapi.service;

import com.github.shary2023.core.component.patcher.EntityPatcher;
import com.github.shary2023.docs.model.ItemSchema;
import com.github.shary2023.docs.model.OrderSchema;
import com.github.shary2023.docs.model.SubcategorySchema;
import com.shary.coreapi.model.dto.item.ItemDataForRent;
import com.shary.coreapi.repository.ItemRepository;
import com.shary.coreapi.repository.entity.item.Item;
import com.shary.coreapi.repository.entity.order.Order;
import com.shary.coreapi.repository.entity.order.support.OrderStatus;
import com.shary.coreapi.util.component.event.publisher.OrderEventPublisher;
import com.shary.coreapi.util.exception.*;
import com.shary.coreapi.util.mapper.ItemMapper;
import com.shary.coreapi.util.mapper.OrderMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository repository;
    private final SubcategoryService subcategoryService;

    private final ItemMapper itemMapper;
    private final OrderMapper orderMapper;

    private final EntityPatcher<Item> itemPatcher;
    private final OrderEventPublisher publisher;

    private final LocalDateTime NOW = LocalDateTime.now();

    @Value("${app.messages.errors.item.by-id-not-found}")
    private String itemByIdNotFoundError;

    @Value("${app.messages.errors.item.not-available}")
    private String itemNotAvailable;

    @Value("${app.messages.link.profile}")
    private String profileFormLink;

    /**
     * Save the item to the database according to the item model received from the user
     *
     * @param itemSchema - Item model received from the user.
     * @return Model of the item saved in the database.
     * @throws MethodArgumentNotValidException - When model fields fail validation
     * @throws FailedItemVerificationException - When the item has not been verified
     */
    public ItemSchema createPreparedForRentItem(ItemSchema itemSchema)
            throws MethodArgumentNotValidException, FailedItemVerificationException {
        //todo нужно написать кастомную валидацию сущностей, добавить проверку верификации предмета.

        if (isCategoryContainsSubcategory(getCategoryId(itemSchema), getSubcategoryId(itemSchema))) {
            @Valid Item item = itemMapper.toItem(itemSchema);
            if (isItemNotRent(item)) {
                return itemMapper.toSchema(item);
            }
        }

        throw new EntityCouldNotBeCreatedException("This subcategory belongs to other categories of items");
    }

    /**
     * @param categoryId    The item category id.
     * @param subcategoryId The item subcategory id.
     * @return boolean : Whether the subcategory belongs to the category of items.
     */
    private boolean isCategoryContainsSubcategory(Long categoryId, Long subcategoryId) {
        List<SubcategorySchema> subcategoriesForCategory =
                subcategoryService.getAllSubcategoriesForCategory(categoryId);
        for (SubcategorySchema schema : subcategoriesForCategory) {
            if (Objects.equals(schema.getId(), subcategoryId)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param itemSchema - Item model received from the user.
     * @return Item category id obtained from its model
     */
    private long getCategoryId(ItemSchema itemSchema) {
        return itemSchema.getCategory().getId();
    }

    /**
     * @param itemSchema - Item model received from the user.
     * @return Item subcategory id obtained from its model
     */
    private long getSubcategoryId(ItemSchema itemSchema) {
        return itemSchema.getSubcategory().getId();
    }

    /**
     * @param item - Item entity
     * @return boolean : Is it possible to rent the item now
     */
    private boolean isItemNotRent(Item item) {
        if (item.getRenterPhone() != null) {
            throw new ItemNotAvailableForRentException(
                    String.format("Looks like this item with id %s is on loan.",
                            item.getId()
                    )
            );
        }
        return true;
    }

    /**
     * Rent the item by unique item ID.
     * @param itemId      - the unique ID of item entity
     * @param renterPhone - phone of user(renter)
     * @return item entity model(dto)
     * @throws FailedItemVerificationException - When the item has not been verified
     */
    //todo добавить верификацию предмета
    @SneakyThrows
    public ItemSchema rentAnItem(OrderSchema orderSchema, String renterPhone)
            throws FailedItemVerificationException {
        Item item = orderSchema.getDelivery().getDeliveryData().getItem();
        item.setRenterPhone(renterPhone);

        if (validateIncomingDataForRentAnItem(item, renterPhone) && item.isVerified()) {
            prepareItemFieldsForRentUp(item, renterPhone);
            Order order = orderMapper.toOrder(orderSchema);
            publisher.publishOrderEvent(order);
            item.setOrder(order);
            repository.save(item);
            return itemMapper.toSchema(item);
        }
        throw new FailedItemVerificationException("Item not verified");
    }

    private Order getOrderFromUser(OrderSchema orderSchema) {
        Order order = orderMapper.toOrder(orderSchema);
        publisher.publishOrderEvent(order);
        return order;
    }

    /**
     * @param item        - Item entity.
     * @param renterPhone - Phone number of the user renting the item.
     * @return boolean : Is it possible to rent the item now.
     */
    private boolean validateIncomingDataForRentAnItem(Item item, String renterPhone) {
        String ownerPhone = item.getOwnerPhone();

        if (Objects.equals(ownerPhone, renterPhone)) {
            throw new UnsupportedRentServiceException();
        }

        if (item.getData().isOnLease()) {
            throw new ItemNotAvailableForRentException(String.format(itemNotAvailable, item.getName()));
        }
        return true;
    }

    /**
     * Prepare an item for rent.
     *
     * @param item        - Item entity.
     * @param renterPhone - Phone number of the user renting the item.
     */
    private void prepareItemFieldsForRentUp(Item item, String renterPhone) {
        item.setRenterPhone(renterPhone);
        ItemDataForRent rentData =
                getItemDataForRent(item, true, true, item.getData().isNeedInsurance());
        // todo подумать, откуда придет этот параметр: isNeedInsurance
        item.setData(rentData);
        item.setVerified(false);
    }

    /**
     * Return the item (end the rental).
     *
     * @param itemId unique item identifier
     * @return updated list of items rented by the user.
     * @throws FailedItemVerificationException - When the item has not been verified
     */
    //todo добавить верификацию предмета
    public boolean returnRentedItem(Long itemId) throws FailedItemVerificationException {
        Item item = get(itemId);
        Item updated = update(itemId, prepareItemFieldForReturnToOwner(item))
                .orElseThrow(
                        () -> new UpdateRequestException(
                                "Error when trying to end an item lease")
                );
        if (updated == null) return false;

        Order order = findOrderByItemId(itemId);
        closeOrderForRenter(order);
        item.setOrder(null);
        repository.save(item);
        order.getItems().remove(item);
        publisher.publishOrderEvent(order);
        return true;
    }

    //todo Is delete?
//    private OrderModel createOrderModel(Item item) {
//        return OrderModel.builder()
//                .status(OrderStatus.NONE)
//                .itemId(item.getId())
//                .ownerPhone(item.getOwnerPhone())
//                .renterPhone(item.getRenterPhone())
//                .creation(NOW)
//                .updated(NOW)
//                .active(true)
////                .isAgreeWithFullDeposit()
////                .deliveryDataTime()
////                .fullPrice()
////                .isRenterNew()
////                .isPrepaymentGet()
////                .isNeedCourier()
////                .delivery()
////                .rentStart()
//                .build();
//    }

    /**
     * @param itemId - Unique item id
     * @return order entity
     */
    private Order findOrderByItemId(Long itemId) {
        return get(itemId).getOrder();
    }

    /**
     * @param itemId - Unique item id
     * @param update - Item for update item entity
     * @return Optional of updated item
     */
    private Optional<Item> update(Long itemId, Item update) {
        Item itemForUpdate = get(itemId);
        Map<String, Object> toUpdateMap = itemPatcher.toUpdateMap(update);
        itemForUpdate = itemPatcher.patch(itemForUpdate, toUpdateMap);
        return Optional.of(repository.save(itemForUpdate));
    }

    /**
     * @param item - item entity
     * @return updated item
     */
    private Item prepareItemFieldForReturnToOwner(Item item) {
        item.setData(getItemDataForRent(item, false, false, false));
        item.setVerified(true);
        item.setRenterPhone(null);
        return repository.save(item);
    }

    /**
     * @param item - changeable item entity
     * @param onLease - Is item on lease?
     * @param isBooked - Is item booked?
     * @param isNeedInsurance - Is need Insurance for rented item?
     * @return data for item rent
     */
    private ItemDataForRent getItemDataForRent(Item item, boolean onLease, boolean isBooked, boolean isNeedInsurance) {
        return ItemDataForRent.builder()
                .onLease(onLease)
                .isBooked(isBooked)
                .isNeedInsurance(isNeedInsurance)
                .onLeaseDate(item.getData().getOnLeaseDate())
                .complete(item.getData().getComplete())
                .isBookedDate(item.getData().getIsBookedDate())
                .build();
    }

    /**
     * @param order - closed order
     */
    //todo Возможно, нужно будет обновить еще какие-то поля у заказа
    private void closeOrderForRenter(Order order) {
        if (order == null || order.getRenterPhone() == null) {
            throw new UnsupportedRentServiceException("This item is not rented");
        }

        order.setStatus(OrderStatus.RETURNED_TO_THE_COURIER);
        order.setUpdated(NOW);
        order.setRentEnd(NOW);
        order.setActive(false);
        publisher.publishOrderEvent(order);
        log.info("Closed order with id {} for renter with id{} : {}", order.getId(), order.getRenterPhone(), order);
    }

    /**
     * @param itemId - Unique item id
     * @return Item entity by id
     */
    private Item get(Long itemId) {
        return repository.findById(itemId).orElseThrow(() -> new ResourceNotFoundException(String.format(itemByIdNotFoundError, itemId)));
    }

    /**
     * @return All item models from item in db
     */
    public List<ItemSchema> getAllItems() {
        return itemMapper.toSchema(repository.findAll());
    }

    /**
     * @param categoryId Unique category id
     * @return All item models in a category from item in db
     */
    public List<ItemSchema> getAllItemsForCategory(Long categoryId) {
        return itemMapper.toSchema(repository.getAllItemsByCategoryId(categoryId));
    }

    /**
     * @param subcategoryId Unique subcategory id
     * @return All item models in a subcategory from item in db
     */
    public List<ItemSchema> getAllItemsForSubcategory(Long subcategoryId) {
        return itemMapper.toSchema(repository.getAllItemsBySubcategoryId(subcategoryId));
    }

    /**
     * Get rented items for user(renter) by user id
     * @param renterPhone - unique user(renter) entity phone.
     * @return list of rented items for renter
     */
    public List<ItemSchema> getRentedItemsForUserByUserPhone(String renterPhone) {
        return itemMapper.toSchema(repository.getRentedItemsByUserPhone(renterPhone));
    }

    /**
     * Get items rented for user(owner) by user id
     * @param ownerPhone - unique user(owner) entity phone.
     * @return list of items for rent for owner
     */
    public List<ItemSchema> getItemsRentedForUserByUserId(String ownerPhone) {
        return itemMapper.toSchema(repository.getItemsForRentByUserPhone(ownerPhone));
    }

    public ItemSchema getItemById(Long itemId) {
        return itemMapper.toSchema(repository.findById(itemId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format(itemByIdNotFoundError, itemId))
                )
        );
    }

    public ItemSchema updateItem(ItemSchema itemSchema) throws MethodArgumentNotValidException {
        return itemMapper.toSchema(itemPatcher.patch(get(itemSchema.getId()), itemPatcher.toUpdateMap(itemMapper.toItem(itemSchema))));
    }

    public boolean delete(Long itemId) {
        Item item = get(itemId);
        if (item == null || item.getData().isOnLease()) {
            return false;
        } else {
            repository.delete(item);
            return true;
        }
    }

}
