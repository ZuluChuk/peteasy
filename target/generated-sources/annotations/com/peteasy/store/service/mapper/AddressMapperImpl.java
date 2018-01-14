package com.peteasy.store.service.mapper;

import com.peteasy.store.domain.Address;

import com.peteasy.store.domain.Country;

import com.peteasy.store.domain.Customer;

import com.peteasy.store.service.dto.AddressDTO;

import java.util.ArrayList;

import java.util.List;

import javax.annotation.Generated;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

@Generated(

    value = "org.mapstruct.ap.MappingProcessor",

    date = "2017-09-07T20:47:29+0100",

    comments = "version: 1.1.0.Final, compiler: javac, environment: Java 1.8.0_144 (Oracle Corporation)"

)

@Component

public class AddressMapperImpl implements AddressMapper {

    @Autowired

    private CountryMapper countryMapper;

    @Autowired

    private CustomerMapper customerMapper;

    @Override

    public List<Address> toEntity(List<AddressDTO> dtoList) {

        if ( dtoList == null ) {

            return null;
        }

        List<Address> list = new ArrayList<Address>();

        for ( AddressDTO addressDTO : dtoList ) {

            list.add( toEntity( addressDTO ) );
        }

        return list;
    }

    @Override

    public List<AddressDTO> toDto(List<Address> entityList) {

        if ( entityList == null ) {

            return null;
        }

        List<AddressDTO> list = new ArrayList<AddressDTO>();

        for ( Address address : entityList ) {

            list.add( toDto( address ) );
        }

        return list;
    }

    @Override

    public AddressDTO toDto(Address address) {

        if ( address == null ) {

            return null;
        }

        AddressDTO addressDTO_ = new AddressDTO();

        addressDTO_.setShippingCustomerId( addressShippingCustomerId( address ) );

        addressDTO_.setBillingCustomerId( addressBillingCustomerId( address ) );

        addressDTO_.setCountryId( addressCountryId( address ) );

        addressDTO_.setId( address.getId() );

        addressDTO_.setAddressLine1( address.getAddressLine1() );

        addressDTO_.setAddressLine2( address.getAddressLine2() );

        addressDTO_.setCity( address.getCity() );

        return addressDTO_;
    }

    @Override

    public Address toEntity(AddressDTO addressDTO) {

        if ( addressDTO == null ) {

            return null;
        }

        Address address_ = new Address();

        address_.setCountry( countryMapper.fromId( addressDTO.getCountryId() ) );

        address_.setShippingCustomer( customerMapper.fromId( addressDTO.getShippingCustomerId() ) );

        address_.setBillingCustomer( customerMapper.fromId( addressDTO.getBillingCustomerId() ) );

        address_.setId( addressDTO.getId() );

        address_.setAddressLine1( addressDTO.getAddressLine1() );

        address_.setAddressLine2( addressDTO.getAddressLine2() );

        address_.setCity( addressDTO.getCity() );

        return address_;
    }

    private Long addressShippingCustomerId(Address address) {

        if ( address == null ) {

            return null;
        }

        Customer shippingCustomer = address.getShippingCustomer();

        if ( shippingCustomer == null ) {

            return null;
        }

        Long id = shippingCustomer.getId();

        if ( id == null ) {

            return null;
        }

        return id;
    }

    private Long addressBillingCustomerId(Address address) {

        if ( address == null ) {

            return null;
        }

        Customer billingCustomer = address.getBillingCustomer();

        if ( billingCustomer == null ) {

            return null;
        }

        Long id = billingCustomer.getId();

        if ( id == null ) {

            return null;
        }

        return id;
    }

    private Long addressCountryId(Address address) {

        if ( address == null ) {

            return null;
        }

        Country country = address.getCountry();

        if ( country == null ) {

            return null;
        }

        Long id = country.getId();

        if ( id == null ) {

            return null;
        }

        return id;
    }
}

