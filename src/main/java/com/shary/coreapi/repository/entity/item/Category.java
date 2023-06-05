package com.shary.coreapi.repository.entity.item;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.github.shary2023.docs.model.CategorySchema;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Table
@Entity(name = "categories")
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties("subcategories")
@SequenceGenerator(allocationSize = 1, name = "category_seq", sequenceName = "category_seq")
public class Category extends CategorySchema {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_seq")
    @Min(value = 1, message = "Minimum allowable id value: 1")
    @Max(value = Long.MAX_VALUE, message = "Maximum allowable id value: " + Long.MAX_VALUE)
    private Long id;

    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Name should not be empty")
    @Size(min = 2, max = 50, message = "Name should be between 2 and 50 characters")
    @Column(unique = true, nullable = false)
    private String name;

    @NotNull(message = "Link to image url can not be empty")
    private URI image;

    @OneToMany(mappedBy = "category",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Item> items = new ArrayList<>();

    @OneToMany(mappedBy = "category",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Subcategory> subcategories = new ArrayList<>();

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image=" + image +
                '}';
    }
}
