package com.shary.coreapi.repository.entity.item;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shary.coreapi.model.dto.item.Characteristics;
import com.shary.coreapi.model.dto.item.ItemDataForRent;
import com.shary.coreapi.repository.entity.order.Order;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.io.Serializable;
import java.net.URI;

@Getter
@Setter
@Table
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "items")
@SequenceGenerator(allocationSize = 1, name = "item_seq", sequenceName = "item_seq")
public class Item implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_seq")
    @Min(value = 1, message = "Minimum allowable id value: 1")
    @Max(value = Long.MAX_VALUE, message = "Maximum allowable id value: " + Long.MAX_VALUE)
    private Long id;

    @NotNull(message = "Name cannot be null")
    @Size(min = 2, max = 50, message = "Name should be between 2 and 50 characters")
    private String name;

    @Size(min = 2, max = 50, message = "Serial number should be between 2 and 50 characters")
    private String serialNumber;

    @NotNull(message = "Photo cannot be null")
    private URI photo;

    @NotNull(message = "Video cannot be null")
    private URI video;

    @Column(columnDefinition = "text", length = 10485760)
    private String description;
    private boolean verified = false;
    private String price;
    private boolean isExtendLease;

    /**
     * Renter - the user who rented the item
     */
    private String renterPhone;

    /**
     * Owner - user, owner of the rented item
     */
    @NotNull(message = "Owner cannot be null")
    private String ownerPhone;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "subcategory_id")
    @NotNull(message = "The item must belong to a subcategory")
    private Subcategory subcategory;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id")
    @NotNull(message = "The item must belong to a category")
    private Category category;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private Order order;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private Characteristics characteristics;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "item_data_for_rent", columnDefinition = "jsonb")
    private ItemDataForRent data;

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                ", photo=" + photo +
                ", video=" + video +
                ", description='" + description + '\'' +
                ", verified=" + verified +
                ", price='" + price + '\'' +
                ", isExtendLease=" + isExtendLease +
                ", renterPhone='" + renterPhone + '\'' +
                ", ownerPhone='" + ownerPhone + '\'' +
                ", subcategory=" + subcategory +
                ", category=" + category +
                ", order=" + order +
                ", characteristics=" + characteristics +
                ", data=" + data +
                '}';
    }
}
