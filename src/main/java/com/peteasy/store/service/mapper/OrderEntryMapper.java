package com.peteasy.store.service.mapper;

import com.peteasy.store.domain.*;
import com.peteasy.store.service.dto.OrderEntryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity OrderEntry and its DTO OrderEntryDTO.
 */
@Mapper(componentModel = "spring", uses = {ProductMapper.class, AbstractOrderMapper.class, })
public interface OrderEntryMapper extends EntityMapper <OrderEntryDTO, OrderEntry> {

    @Mapping(source = "product.id", target = "productId")

    @Mapping(source = "order.id", target = "orderId")
    OrderEntryDTO toDto(OrderEntry orderEntry); 

    @Mapping(source = "productId", target = "product")

    @Mapping(source = "orderId", target = "order")
    OrderEntry toEntity(OrderEntryDTO orderEntryDTO); 
    default OrderEntry fromId(Long id) {
        if (id == null) {
            return null;
        }
        OrderEntry orderEntry = new OrderEntry();
        orderEntry.setId(id);
        return orderEntry;
    }
}
