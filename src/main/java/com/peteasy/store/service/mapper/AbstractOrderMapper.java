package com.peteasy.store.service.mapper;

import com.peteasy.store.domain.*;
import com.peteasy.store.service.dto.AbstractOrderDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AbstractOrder and its DTO AbstractOrderDTO.
 */
@Mapper(componentModel = "spring", uses = {AddressMapper.class, })
public interface AbstractOrderMapper extends EntityMapper <AbstractOrderDTO, AbstractOrder> {

    @Mapping(source = "shippingAddress.id", target = "shippingAddressId")

    @Mapping(source = "billingAddress.id", target = "billingAddressId")
    AbstractOrderDTO toDto(AbstractOrder abstractOrder); 

    @Mapping(source = "shippingAddressId", target = "shippingAddress")

    @Mapping(source = "billingAddressId", target = "billingAddress")
    @Mapping(target = "entries", ignore = true)
    @Mapping(target = "order", ignore = true)
    @Mapping(target = "cart", ignore = true)
    AbstractOrder toEntity(AbstractOrderDTO abstractOrderDTO); 
    default AbstractOrder fromId(Long id) {
        if (id == null) {
            return null;
        }
        AbstractOrder abstractOrder = new AbstractOrder();
        abstractOrder.setId(id);
        return abstractOrder;
    }
}
