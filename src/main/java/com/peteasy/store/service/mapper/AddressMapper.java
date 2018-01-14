package com.peteasy.store.service.mapper;

import com.peteasy.store.domain.*;
import com.peteasy.store.service.dto.AddressDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Address and its DTO AddressDTO.
 */
@Mapper(componentModel = "spring", uses = {CountryMapper.class, CustomerMapper.class, })
public interface AddressMapper extends EntityMapper <AddressDTO, Address> {

    @Mapping(source = "country.id", target = "countryId")

    @Mapping(source = "shippingCustomer.id", target = "shippingCustomerId")

    @Mapping(source = "billingCustomer.id", target = "billingCustomerId")
    AddressDTO toDto(Address address); 

    @Mapping(source = "countryId", target = "country")

    @Mapping(source = "shippingCustomerId", target = "shippingCustomer")

    @Mapping(source = "billingCustomerId", target = "billingCustomer")
    Address toEntity(AddressDTO addressDTO); 
    default Address fromId(Long id) {
        if (id == null) {
            return null;
        }
        Address address = new Address();
        address.setId(id);
        return address;
    }
}
