package com.peteasy.store.service.mapper;

import com.peteasy.store.domain.*;
import com.peteasy.store.service.dto.OrderDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Order and its DTO OrderDTO.
 */
@Mapper(componentModel = "spring", uses = {AbstractOrderMapper.class, CustomerMapper.class, })
public interface OrderMapper extends EntityMapper <OrderDTO, Order> {

    @Mapping(source = "abstractOrder.id", target = "abstractOrderId")

    @Mapping(source = "customer.id", target = "customerId")
    OrderDTO toDto(Order order); 

    @Mapping(source = "abstractOrderId", target = "abstractOrder")

    @Mapping(source = "customerId", target = "customer")
    Order toEntity(OrderDTO orderDTO); 
    default Order fromId(Long id) {
        if (id == null) {
            return null;
        }
        Order order = new Order();
        order.setId(id);
        return order;
    }
}
