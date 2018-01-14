package com.peteasy.store.service.mapper;

import com.peteasy.store.domain.AbstractOrder;

import com.peteasy.store.domain.Customer;

import com.peteasy.store.domain.Order;

import com.peteasy.store.service.dto.OrderDTO;

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

public class OrderMapperImpl implements OrderMapper {

    @Autowired

    private AbstractOrderMapper abstractOrderMapper;

    @Autowired

    private CustomerMapper customerMapper;

    @Override

    public List<Order> toEntity(List<OrderDTO> dtoList) {

        if ( dtoList == null ) {

            return null;
        }

        List<Order> list = new ArrayList<Order>();

        for ( OrderDTO orderDTO : dtoList ) {

            list.add( toEntity( orderDTO ) );
        }

        return list;
    }

    @Override

    public List<OrderDTO> toDto(List<Order> entityList) {

        if ( entityList == null ) {

            return null;
        }

        List<OrderDTO> list = new ArrayList<OrderDTO>();

        for ( Order order : entityList ) {

            list.add( toDto( order ) );
        }

        return list;
    }

    @Override

    public OrderDTO toDto(Order order) {

        if ( order == null ) {

            return null;
        }

        OrderDTO orderDTO_ = new OrderDTO();

        orderDTO_.setCustomerId( orderCustomerId( order ) );

        orderDTO_.setAbstractOrderId( orderAbstractOrderId( order ) );

        orderDTO_.setId( order.getId() );

        orderDTO_.setCode( order.getCode() );

        return orderDTO_;
    }

    @Override

    public Order toEntity(OrderDTO orderDTO) {

        if ( orderDTO == null ) {

            return null;
        }

        Order order_ = new Order();

        order_.setAbstractOrder( abstractOrderMapper.fromId( orderDTO.getAbstractOrderId() ) );

        order_.setCustomer( customerMapper.fromId( orderDTO.getCustomerId() ) );

        order_.setId( orderDTO.getId() );

        order_.setCode( orderDTO.getCode() );

        return order_;
    }

    private Long orderCustomerId(Order order) {

        if ( order == null ) {

            return null;
        }

        Customer customer = order.getCustomer();

        if ( customer == null ) {

            return null;
        }

        Long id = customer.getId();

        if ( id == null ) {

            return null;
        }

        return id;
    }

    private Long orderAbstractOrderId(Order order) {

        if ( order == null ) {

            return null;
        }

        AbstractOrder abstractOrder = order.getAbstractOrder();

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

