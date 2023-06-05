package com.shary.coreapi.util.mapper;

import com.github.shary2023.docs.model.OrderSchema;
import com.shary.coreapi.model.request.impl.order.OrderModel;
import com.shary.coreapi.repository.entity.order.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderMapper {
    Order toOrder(OrderModel model);

    OrderModel toModel(Order order);

    List<OrderSchema> toSchema(List<Order> orders);

    @Mapping(target = "creation", source = "order", qualifiedByName = "toCreationOffsetDataTime")
    @Mapping(target = "updated", source = "order", qualifiedByName = "toUpdatedOffsetDataTime")
    @Mapping(target = "rentEnd", source = "order", qualifiedByName = "toRentEndOffsetDataTime")
    OrderSchema toSchema(Order order);

    @Mapping(target = "creation", source = "orderSchema", qualifiedByName = "toCreationLocalDataTime")
    @Mapping(target = "updated", source = "orderSchema", qualifiedByName = "toUpdatedLocalDataTime")
    @Mapping(target = "rentEnd", source = "orderSchema", qualifiedByName = "toRentEndLocalDataTime")
    Order toOrder(OrderSchema orderSchema);

    @Named("toCreationLocalDataTime")
    default LocalDateTime toCreationLocalDataTime(OrderSchema orderSchema) {
        return orderSchema.getCreation() == null ? null : orderSchema.getCreation().toZonedDateTime().toLocalDateTime();
    }

    @Named("toUpdatedLocalDataTime")
    default LocalDateTime toUpdatedLocalDataTime(OrderSchema orderSchema) {
        return orderSchema.getUpdated() == null ? null : orderSchema.getUpdated().toZonedDateTime().toLocalDateTime();
    }

    @Named("toRentEndLocalDataTime")
    default LocalDateTime toRentEndLocalDataTime(OrderSchema orderSchema) {
        return orderSchema.getRentEnd() == null ? null : orderSchema.getRentEnd().toZonedDateTime().toLocalDateTime();
    }

    @Named("toCreationOffsetDataTime")
    default OffsetDateTime toCreationOffsetDataTime(Order order) {
        return order.getCreation().atOffset(ZoneOffset.UTC);
    }

    @Named("toUpdatedOffsetDataTime")
    default OffsetDateTime toUpdatedOffsetDataTime(Order order) {
        return order.getUpdated().atOffset(ZoneOffset.UTC);
    }

    @Named("toRentEndOffsetDataTime")
    default OffsetDateTime toRentEndOffsetDataTime(Order order) {
        return order.getRentEnd() == null ? null : order.getRentEnd().atOffset(ZoneOffset.UTC);
    }
}
