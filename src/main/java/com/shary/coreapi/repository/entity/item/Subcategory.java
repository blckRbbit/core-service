package com.shary.coreapi.repository.entity.item;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Table
@Entity(name = "subcategories")
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties("items")
@SequenceGenerator(allocationSize = 1, name = "subcategory_seq", sequenceName = "subcategory_seq")
public class Subcategory {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subcategory_seq")
    @Min(value = 1, message = "Minimum allowable id value: 1")
    @Max(value = Long.MAX_VALUE, message = "Maximum allowable id value: " + Long.MAX_VALUE)
    private Long id;

    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Name should not be empty")
    private String name;

    @NotNull(message = "Link to image url can not be empty")
    private URI image;

    @OneToMany(mappedBy = "subcategory",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Item> items = new ArrayList<>();

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    @NotNull(message = "Category can not be null")
    private Category category;

    @Override
    public String toString() {
        return "Subcategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image=" + image +
                ", category=" + category +
                '}';
    }
}