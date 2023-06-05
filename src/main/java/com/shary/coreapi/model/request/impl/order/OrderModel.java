package com.shary.coreapi.model.request.impl.order;

import com.shary.coreapi.model.dto.order.Delivery;
import com.shary.coreapi.model.request.Model;
import com.shary.coreapi.repository.entity.order.support.OrderStatus;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
public class OrderModel implements Model {
    @Min(value = 1, message = "Minimum allowable order id value: 1")
    @Max(value = Long.MAX_VALUE, message = "Maximum allowable order id value: " + Long.MAX_VALUE)
    private Long id;
    @NotNull(message = "Order status cannot be null")
    private OrderStatus status;
    @NotNull(message = "Item id cannot be null")
    private Long itemId;
    @NotNull(message = "Owner id cannot be null")
    private String ownerPhone;
    @NotNull(message = "Renter phone cannot be null")
    private String renterPhone;
    private LocalDateTime creation;
    private LocalDateTime updated;
    private boolean isAgreeWithFullDeposit;
    private LocalDateTime deliveryDataTime;
    private String fullPrice;
    private boolean isRenterNew;
    private boolean isPrepaymentGet;
    private boolean isNeedCourier;
    private Delivery delivery;
    private LocalDateTime rentStart;
    private LocalDateTime rentEnd;
    private boolean isActive;

    private OrderModel() {
    }

    private OrderModel(OrderModelBuilder builder) {
        this.id = builder.id;
        this.status = builder.status;
        this.itemId = builder.itemId;
        this.renterPhone = builder.renterPhone;
        this.ownerPhone = builder.ownerPhone;
        this.creation = builder.creation;
        this.updated = builder.updated;
        this.isAgreeWithFullDeposit = builder.isAgreeWithFullDeposit;
        this.deliveryDataTime = builder.deliveryDataTime;
        this.fullPrice = builder.fullPrice;
        this.isRenterNew = builder.isRenterNew;
        this.isPrepaymentGet =builder.isPrepaymentGet;
        this.isNeedCourier = builder.isNeedCourier;
        this.delivery = builder.delivery;
        this.rentStart = builder.rentStart;
        this.rentEnd = builder.rentEnd;
        this.isActive = builder.active;
    }

    public static OrderModelBuilder builder() {
        return new OrderModelBuilder();
    }

    public static class OrderModelBuilder {
        private Long id;
        private OrderStatus status;
        private Long itemId;
        private String ownerPhone;
        private String renterPhone;
        private LocalDateTime creation;
        private LocalDateTime updated;
        private String fullPrice;
        private boolean isAgreeWithFullDeposit;
        private boolean isRenterNew;
        private boolean isPrepaymentGet;
        private boolean isNeedCourier;
        private Delivery delivery;
        private LocalDateTime deliveryDataTime;
        private LocalDateTime rentStart;
        private LocalDateTime rentEnd;
        private boolean active;

        public OrderModelBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public OrderModelBuilder status(OrderStatus status) {
            this.status = status;
            return this;
        }

        public OrderModelBuilder itemId(Long itemId) {
            this.itemId = itemId;
            return this;
        }

        public OrderModelBuilder ownerPhone(String ownerPhone) {
            this.ownerPhone = ownerPhone;
            return this;
        }

        public OrderModelBuilder fullPrice(String fullPrice) {
            this.fullPrice = fullPrice;
            return this;
        }

        public OrderModelBuilder renterPhone(String renterPhone) {
            this.renterPhone = renterPhone;
            return this;
        }

        public OrderModelBuilder creation(LocalDateTime creation) {
            this.creation = creation;
            return this;
        }

        public OrderModelBuilder updated(LocalDateTime update) {
            this.updated = update;
            return this;
        }

        public OrderModelBuilder deliveryDataTime(LocalDateTime deliveryDataTime) {
            this.deliveryDataTime = deliveryDataTime;
            return this;
        }

        public OrderModelBuilder rentStart(LocalDateTime rentStart) {
            this.rentStart = rentStart;
            return this;
        }

        public OrderModelBuilder rentEnd(LocalDateTime rentEnd) {
            this.rentEnd = rentEnd;
            return this;
        }

        public OrderModelBuilder active(boolean active) {
            this.active = active;
            return this;
        }

        public OrderModelBuilder isAgreeWithFullDeposit(boolean isAgreeWithFullDeposit) {
            this.isAgreeWithFullDeposit = isAgreeWithFullDeposit;
            return this;
        }

        public OrderModelBuilder isRenterNew(boolean isRenterNew) {
            this.isRenterNew = isRenterNew;
            return this;
        }

        public OrderModelBuilder isPrepaymentGet(boolean isPrepaymentGet) {
            this.isPrepaymentGet = isPrepaymentGet;
            return this;
        }

        public OrderModelBuilder isNeedCourier(boolean isNeedCourier) {
            this.isNeedCourier = isNeedCourier;
            return this;
        }

        public OrderModelBuilder delivery(Delivery delivery) {
            this.delivery = delivery;
            return this;
        }

        public OrderModel build() {
            return new OrderModel(this);
        }
    }
}
