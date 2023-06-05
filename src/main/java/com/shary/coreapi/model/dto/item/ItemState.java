package com.shary.coreapi.model.dto.item;

import java.io.Serializable;

public class ItemState implements Serializable {
    // поля для описания состояния предмета?
    private String description;

    public ItemState() {
    }

    private ItemState(ItemStateBuilder builder) {
        this.description = builder.description;
    }

    public static ItemStateBuilder builder() {return new ItemStateBuilder();}

    public static class ItemStateBuilder {
        private String description;

        public ItemStateBuilder() {
        }

        public ItemStateBuilder description(String description) {
            this.description = description;
            return this;
        }

        public ItemState build() {
            return new ItemState(this);
        }
    }

    @Override
    public String toString() {
        return "ItemState{" +
                "description='" + description + '\'' +
                '}';
    }
}
