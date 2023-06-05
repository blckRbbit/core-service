package com.shary.coreapi.repository.entity.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shary.coreapi.model.dto.order.Delivery;
import com.shary.coreapi.repository.entity.item.Item;
import com.shary.coreapi.repository.entity.order.support.OrderStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table
@Getter
@Setter
@NoArgsConstructor
@Entity(name = "orders")
@SequenceGenerator(allocationSize = 1, name = "order_seq", sequenceName = "order_seq")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_seq")
    @Min(value = 1, message = "Minimum allowable order id value: 1")
    @Max(value = Long.MAX_VALUE, message = "Maximum allowable order id value: " + Long.MAX_VALUE)
    private Long id;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Order status cannot be null")
    private OrderStatus status;

    private boolean isAgreeWithFullDeposit;
    private LocalDateTime rentStart;
    @UpdateTimestamp
    private LocalDateTime rentEnd;
    private String fullPrice;
    private boolean isRenterNew;
    private boolean isPrepaymentGet;
    private boolean isNeedCourier;

    @JsonIgnore
    @OneToMany(mappedBy = "order",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Item> items = new ArrayList<>();
    private String renterPhone;

    @CreationTimestamp
    private LocalDateTime creation;
    @UpdateTimestamp
    private LocalDateTime updated;

    @Column(name = "active")
    private boolean active;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private Delivery delivery;

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", status=" + status +
                ", isAgreeWithFullDeposit=" + isAgreeWithFullDeposit +
                ", rentStart=" + rentStart +
                ", rentEnd=" + rentEnd +
                ", fullPrice='" + fullPrice + '\'' +
                ", isRenterNew=" + isRenterNew +
                ", isPrepaymentGet=" + isPrepaymentGet +
                ", isNeedCourier=" + isNeedCourier +
                ", items=" + items +
                ", renterPhone='" + renterPhone + '\'' +
                ", creation=" + creation +
                ", updated=" + updated +
                ", active=" + active +
                '}';
    }
}
