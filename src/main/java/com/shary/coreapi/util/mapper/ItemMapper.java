package com.shary.coreapi.util.mapper;

import com.github.shary2023.docs.model.ItemSchema;
import com.shary.coreapi.repository.entity.item.Item;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ItemMapper {

    Item toItem(ItemSchema itemSchema);

    ItemSchema toSchema(Item item);

    List<ItemSchema> toSchema(List<Item> items);

}
