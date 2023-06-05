package com.shary.coreapi.model.dto.order;

import com.github.shary2023.docs.model.ItemSchema;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class DeliveryData implements Serializable {
    private String addressFrom;
    private String addressTo;
    private String openingHours;
    private String phone;
    private ItemSchema item;

    private DeliveryData() {
    }

    private DeliveryData(DeliveryDataBuilder builder) {
        this.addressFrom = builder.addressFrom;
        this.addressTo = builder.addressTo;
        this.openingHours = builder.openingHours;
        this.phone = builder.phone;
        this.item = builder.item;
    }

    public static DeliveryDataBuilder builder() {
        return new DeliveryDataBuilder();
    }

    public static class DeliveryDataBuilder {
        private String addressFrom;
        private String addressTo;
        private String openingHours;
        private String phone;
        private ItemSchema item;

        public DeliveryDataBuilder() {
        }

        public DeliveryDataBuilder addressFrom(String addressFrom) {
            this.addressFrom = addressFrom;
            return this;
        }

        public DeliveryDataBuilder addressTo(String addressTo) {
            this.addressTo = addressTo;
            return this;
        }

        public DeliveryDataBuilder openingHours(String openingHours) {
            this.openingHours = openingHours;
            return this;
        }

        public DeliveryDataBuilder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public DeliveryDataBuilder items(ItemSchema item) {
            this.item = item;
            return this;
        }

        public DeliveryData build() {
            return new DeliveryData(this);
        }
    }

    @Override
    public String toString() {
        return "DeliveryData{" +
                "addressFrom='" + addressFrom + '\'' +
                ", addressTo='" + addressTo + '\'' +
                ", openingHours='" + openingHours + '\'' +
                ", phone='" + phone + '\'' +
                ", item=" + item +
                '}';
    }
}
