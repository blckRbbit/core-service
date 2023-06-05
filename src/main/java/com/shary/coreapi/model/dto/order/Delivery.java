package com.shary.coreapi.model.dto.order;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Delivery implements Serializable {
    private String address;
    private DeliveryData deliveryData;
    private LocalDateTime dateTime;
    private boolean isPickup;

    private Delivery() {
    }

    public static DeliveryBuilder builder() {return new DeliveryBuilder();}

    private Delivery(DeliveryBuilder builder) {
        this.address = builder.address;
        this.deliveryData = builder.deliveryData;
        this.dateTime = builder.dateTime;
        this.isPickup = builder.isPickup;
    }

    public static class DeliveryBuilder {
        private String address;
        private DeliveryData deliveryData;
        private LocalDateTime dateTime;
        private boolean isPickup;

        public DeliveryBuilder() {
        }

        public DeliveryBuilder address(String address) {
            this.address = address;
            return this;
        }

        public DeliveryBuilder deliveryData(DeliveryData deliveryData) {
            this.deliveryData = deliveryData;
            return this;
        }

        public DeliveryBuilder dateTime(LocalDateTime dateTime) {
            this.dateTime = dateTime;
            return this;
        }

        public DeliveryBuilder isPickup(boolean isPickup) {
            this.isPickup = isPickup;
            return this;
        }

        public Delivery build() {
            return new Delivery(this);
        }
    }

    @Override
    public String toString() {
        return "Delivery{" +
                "address='" + address + '\'' +
                ", deliveryData=" + deliveryData +
                ", dateTime=" + dateTime +
                ", isPickup=" + isPickup +
                '}';
    }
}
