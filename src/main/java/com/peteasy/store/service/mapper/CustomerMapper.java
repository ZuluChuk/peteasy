package com.peteasy.store.service.mapper;

import com.peteasy.store.domain.*;
import com.peteasy.store.service.dto.CustomerDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Customer and its DTO CustomerDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CustomerMapper extends EntityMapper <CustomerDTO, Customer> {
    
    @Mapping(target = "orders", ignore = true)
    @Mapping(target = "carts", ignore = true)
    @Mapping(target = "shippingAddresses", ignore = true)
    @Mapping(target = "billingAddresses", ignore = true)
    Customer toEntity(CustomerDTO customerDTO); 
    default Customer fromId(Long id) {
        if (id == null) {
            return null;
        }
        Customer customer = new Customer();
        customer.setId(id);
        return customer;
    }
}
