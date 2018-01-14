package com.peteasy.store.service.mapper;

import com.peteasy.store.domain.*;
import com.peteasy.store.service.dto.CartDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Cart and its DTO CartDTO.
 */
@Mapper(componentModel = "spring", uses = {AbstractOrderMapper.class, CustomerMapper.class, })
public interface CartMapper extends EntityMapper <CartDTO, Cart> {

    @Mapping(source = "abstractOrder.id", target = "abstractOrderId")

    @Mapping(source = "customer.id", target = "customerId")
    CartDTO toDto(Cart cart); 

    @Mapping(source = "abstractOrderId", target = "abstractOrder")

    @Mapping(source = "customerId", target = "customer")
    Cart toEntity(CartDTO cartDTO); 
    default Cart fromId(Long id) {
        if (id == null) {
            return null;
        }
        Cart cart = new Cart();
        cart.setId(id);
        return cart;
    }
}
