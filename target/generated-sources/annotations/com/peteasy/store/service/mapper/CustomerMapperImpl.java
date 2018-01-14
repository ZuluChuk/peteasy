package com.peteasy.store.service.mapper;

import com.peteasy.store.domain.Customer;

import com.peteasy.store.service.dto.CustomerDTO;

import java.util.ArrayList;

import java.util.List;

import javax.annotation.Generated;

import org.springframework.stereotype.Component;

@Generated(

    value = "org.mapstruct.ap.MappingProcessor",

    date = "2017-09-07T20:47:29+0100",

    comments = "version: 1.1.0.Final, compiler: javac, environment: Java 1.8.0_144 (Oracle Corporation)"

)

@Component

public class CustomerMapperImpl implements CustomerMapper {

    @Override

    public CustomerDTO toDto(Customer entity) {

        if ( entity == null ) {

            return null;
        }

        CustomerDTO customerDTO = new CustomerDTO();

        customerDTO.setId( entity.getId() );

        customerDTO.setFirstName( entity.getFirstName() );

        customerDTO.setLastName( entity.getLastName() );

        customerDTO.setEmail( entity.getEmail() );

        customerDTO.setPhoneNumber( entity.getPhoneNumber() );

        customerDTO.setRegistrationDate( entity.getRegistrationDate() );

        return customerDTO;
    }

    @Override

    public List<Customer> toEntity(List<CustomerDTO> dtoList) {

        if ( dtoList == null ) {

            return null;
        }

        List<Customer> list = new ArrayList<Customer>();

        for ( CustomerDTO customerDTO : dtoList ) {

            list.add( toEntity( customerDTO ) );
        }

        return list;
    }

    @Override

    public List<CustomerDTO> toDto(List<Customer> entityList) {

        if ( entityList == null ) {

            return null;
        }

        List<CustomerDTO> list = new ArrayList<CustomerDTO>();

        for ( Customer customer : entityList ) {

            list.add( toDto( customer ) );
        }

        return list;
    }

    @Override

    public Customer toEntity(CustomerDTO customerDTO) {

        if ( customerDTO == null ) {

            return null;
        }

        Customer customer_ = new Customer();

        customer_.setId( customerDTO.getId() );

        customer_.setFirstName( customerDTO.getFirstName() );

        customer_.setLastName( customerDTO.getLastName() );

        customer_.setEmail( customerDTO.getEmail() );

        customer_.setPhoneNumber( customerDTO.getPhoneNumber() );

        customer_.setRegistrationDate( customerDTO.getRegistrationDate() );

        return customer_;
    }
}

