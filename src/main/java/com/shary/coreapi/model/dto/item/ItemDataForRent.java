package com.shary.coreapi.model.dto.item;

import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
public class ItemDataForRent implements Serializable {
    private boolean onLease;
    private boolean isBooked;
    private boolean isNeedInsurance;
    private LocalDateTime onLeaseDate;
    private LocalDateTime isBookedDate;
    private ItemComplete complete;
    private ItemState state;

    public ItemDataForRent() {
    }

    private ItemDataForRent(ItemDataBuilder builder) {
        this.onLease = builder.onLease;
        this.isBooked = builder.isBooked;
        this.isNeedInsurance = builder.isNeedInsurance;
        this.onLeaseDate = builder.onLeaseDate;
        this.isBookedDate = builder.isBookedDate;
        this.complete = builder.complete;
        this.state = builder.state;
    }

    public static ItemDataBuilder builder() {
        return new ItemDataBuilder();
    }

    public static class ItemDataBuilder {
        private boolean onLease;
        private boolean isBooked;
        private boolean isNeedInsurance;
        private LocalDateTime onLeaseDate;
        private LocalDateTime isBookedDate;
        private ItemComplete complete;
        private ItemState state;

        public ItemDataBuilder() {
        }

        public ItemDataBuilder onLease(boolean onLease) {
            this.onLease = onLease;
            return this;
        }

        public ItemDataBuilder isBooked(boolean isBooked) {
            this.isBooked = isBooked;
            return this;
        }

        public ItemDataBuilder isNeedInsurance(boolean isNeedInsurance) {
            this.isNeedInsurance = isNeedInsurance;
            return this;
        }

        public ItemDataBuilder onLeaseDate(LocalDateTime onLeaseDate) {
            this.onLeaseDate = onLeaseDate;
            return this;
        }

        public ItemDataBuilder isBookedDate(LocalDateTime isBookedDate) {
            this.isBookedDate = isBookedDate;
            return this;
        }

        public ItemDataBuilder complete(ItemComplete complete) {
            this.complete = complete;
            return this;
        }

        public ItemDataBuilder state(ItemState state) {
            this.state = state;
            return this;
        }

        public ItemDataForRent build() {
            return new ItemDataForRent(this);
        }
    }

    @Override
    public String toString() {
        return "ItemDataForRent{" +
                "onLease=" + onLease +
                ", isBooked=" + isBooked +
                ", isNeedInsurance=" + isNeedInsurance +
                ", onLeaseDate=" + onLeaseDate +
                ", isBookedDate=" + isBookedDate +
                ", complete=" + complete +
                '}';
    }
}
