package com.shary.coreapi.model.dto.item;

import lombok.Getter;
import java.io.Serializable;

@Getter
public class Characteristics implements Serializable {
    private String brand;
    private String color;
    private String operatingSystem;
    private String cpu;
    private String videoCard;
    private String ram;
    private String memoryType;
    private String memory;
    private String batteryCapacity;
    private String weight;

    /**
     * Default constructor needed to serialize/deserialize JSON.
     */
    private Characteristics() {
    }

    private Characteristics(CharacteristicsBuilder builder) {
        this.brand = builder.brand;
        this.color = builder.color;
        this.operatingSystem = builder.operatingSystem;
        this.cpu = builder.cpu;
        this.videoCard = builder.videoCard;
        this.ram = builder.ram;
        this.memoryType = builder.memoryType;
        this.memory = builder.memory;
        this.batteryCapacity = builder.batteryCapacity;
        this.weight = builder.weight;
    }

    public static CharacteristicsBuilder builder() {
        return new CharacteristicsBuilder();
    }

    public static class CharacteristicsBuilder {
        private String brand;
        private String color;
        private String operatingSystem;
        private String cpu;
        private String videoCard;
        private String ram;
        private String memoryType;
        private String memory;
        private String batteryCapacity;
        private String weight;

        public CharacteristicsBuilder() {
        }

        public CharacteristicsBuilder brand(String brand) {
            this.brand = brand;
            return this;
        }

        public CharacteristicsBuilder color(String color) {
            this.color = color;
            return this;
        }

        public CharacteristicsBuilder operatingSystem(String operatingSystem) {
            this.operatingSystem = operatingSystem;
            return this;
        }

        public CharacteristicsBuilder cpu(String cpu) {
            this.cpu = cpu;
            return this;
        }

        public CharacteristicsBuilder videoCard(String videoCard) {
            this.videoCard = videoCard;
            return this;
        }

        public CharacteristicsBuilder ram(String ram) {
            this.ram = ram;
            return this;
        }

        public CharacteristicsBuilder memoryType(String memoryType) {
            this.memoryType = memoryType;
            return this;
        }

        public CharacteristicsBuilder memory(String memory) {
            this.memory = memory;
            return this;
        }

        public CharacteristicsBuilder batteryCapacity(String batteryCapacity) {
            this.batteryCapacity = batteryCapacity;
            return this;
        }

        public CharacteristicsBuilder weight(String weight) {
            this.weight = weight;
            return this;
        }

        public Characteristics build() {
            return new Characteristics(this);
        }
    }

    @Override
    public String toString() {
        return String
                .format(
                        "characteristics { " +
                                "brand: %s, color: %s, operatingSystem: %s, cpu: %s, videoCard: %s, ram: %s, " +
                                "memoryType: %s, memory: %s, batteryCapacity: %s, weight: %s }",
                        brand, color, operatingSystem, cpu, videoCard, ram, memoryType, memory, batteryCapacity, weight
                );
    }
}
