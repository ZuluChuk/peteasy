package com.peteasy.store.service.mapper;

import com.peteasy.store.domain.AbstractOrder;

import com.peteasy.store.domain.Cart;

import com.peteasy.store.domain.Customer;

import com.peteasy.store.service.dto.CartDTO;

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

public class CartMapperImpl implements CartMapper {

    @Autowired

    private AbstractOrderMapper abstractOrderMapper;

    @Autowired

    private CustomerMapper customerMapper;

    @Override

    public List<Cart> toEntity(List<CartDTO> dtoList) {

        if ( dtoList == null ) {

            return null;
        }

        List<Cart> list = new ArrayList<Cart>();

        for ( CartDTO cartDTO : dtoList ) {

            list.add( toEntity( cartDTO ) );
        }

        return list;
    }

    @Override

    public List<CartDTO> toDto(List<Cart> entityList) {

        if ( entityList == null ) {

            return null;
        }

        List<CartDTO> list = new ArrayList<CartDTO>();

        for ( Cart cart : entityList ) {

            list.add( toDto( cart ) );
        }

        return list;
    }

    @Override

    public CartDTO toDto(Cart cart) {

        if ( cart == null ) {

            return null;
        }

        CartDTO cartDTO_ = new CartDTO();

        cartDTO_.setCustomerId( cartCustomerId( cart ) );

        cartDTO_.setAbstractOrderId( cartAbstractOrderId( cart ) );

        cartDTO_.setId( cart.getId() );

        cartDTO_.setCode( cart.getCode() );

        return cartDTO_;
    }

    @Override

    public Cart toEntity(CartDTO cartDTO) {

        if ( cartDTO == null ) {

            return null;
        }

        Cart cart_ = new Cart();

        cart_.setAbstractOrder( abstractOrderMapper.fromId( cartDTO.getAbstractOrderId() ) );

        cart_.setCustomer( customerMapper.fromId( cartDTO.getCustomerId() ) );

        cart_.setId( cartDTO.getId() );

        cart_.setCode( cartDTO.getCode() );

        return cart_;
    }

    private Long cartCustomerId(Cart cart) {

        if ( cart == null ) {

            return null;
        }

        Customer customer = cart.getCustomer();

        if ( customer == null ) {

            return null;
        }

        Long id = customer.getId();

        if ( id == null ) {

            return null;
        }

        return id;
    }

    private Long cartAbstractOrderId(Cart cart) {

        if ( cart == null ) {

            return null;
        }

        AbstractOrder abstractOrder = cart.getAbstractOrder();

        if ( abstractOrder == null ) {

            return null;
        }

        Long id = abstractOrder.getId();

        if ( id == null ) {

            return null;
        }

        return id;
    }
}

