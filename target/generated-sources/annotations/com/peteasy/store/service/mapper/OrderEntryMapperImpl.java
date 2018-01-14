package com.peteasy.store.service.mapper;

import com.peteasy.store.domain.AbstractOrder;

import com.peteasy.store.domain.OrderEntry;

import com.peteasy.store.domain.Product;

import com.peteasy.store.service.dto.OrderEntryDTO;

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

public class OrderEntryMapperImpl implements OrderEntryMapper {

    @Autowired

    private ProductMapper productMapper;

    @Autowired

    private AbstractOrderMapper abstractOrderMapper;

    @Override

    public List<OrderEntry> toEntity(List<OrderEntryDTO> dtoList) {

        if ( dtoList == null ) {

            return null;
        }

        List<OrderEntry> list = new ArrayList<OrderEntry>();

        for ( OrderEntryDTO orderEntryDTO : dtoList ) {

            list.add( toEntity( orderEntryDTO ) );
        }

        return list;
    }

    @Override

    public List<OrderEntryDTO> toDto(List<OrderEntry> entityList) {

        if ( entityList == null ) {

            return null;
        }

        List<OrderEntryDTO> list = new ArrayList<OrderEntryDTO>();

        for ( OrderEntry orderEntry : entityList ) {

            list.add( toDto( orderEntry ) );
        }

        return list;
    }

    @Override

    public OrderEntryDTO toDto(OrderEntry orderEntry) {

        if ( orderEntry == null ) {

            return null;
        }

        OrderEntryDTO orderEntryDTO_ = new OrderEntryDTO();

        orderEntryDTO_.setProductId( orderEntryProductId( orderEntry ) );

        orderEntryDTO_.setOrderId( orderEntryOrderId( orderEntry ) );

        orderEntryDTO_.setId( orderEntry.getId() );

        orderEntryDTO_.setBasePrice( orderEntry.getBasePrice() );

        orderEntryDTO_.setTotalPrice( orderEntry.getTotalPrice() );

        orderEntryDTO_.setAmont( orderEntry.getAmont() );

        orderEntryDTO_.setNo( orderEntry.getNo() );

        return orderEntryDTO_;
    }

    @Override

    public OrderEntry toEntity(OrderEntryDTO orderEntryDTO) {

        if ( orderEntryDTO == null ) {

            return null;
        }

        OrderEntry orderEntry_ = new OrderEntry();

        orderEntry_.setProduct( productMapper.fromId( orderEntryDTO.getProductId() ) );

        orderEntry_.setOrder( abstractOrderMapper.fromId( orderEntryDTO.getOrderId() ) );

        orderEntry_.setId( orderEntryDTO.getId() );

        orderEntry_.setBasePrice( orderEntryDTO.getBasePrice() );

        orderEntry_.setTotalPrice( orderEntryDTO.getTotalPrice() );

        orderEntry_.setAmont( orderEntryDTO.getAmont() );

        orderEntry_.setNo( orderEntryDTO.getNo() );

        return orderEntry_;
    }

    private Long orderEntryProductId(OrderEntry orderEntry) {

        if ( orderEntry == null ) {

            return null;
        }

        Product product = orderEntry.getProduct();

        if ( product == null ) {

            return null;
        }

        Long id = product.getId();

        if ( id == null ) {

            return null;
        }

        return id;
    }

    private Long orderEntryOrderId(OrderEntry orderEntry) {

        if ( orderEntry == null ) {

            return null;
        }

        AbstractOrder order = orderEntry.getOrder();

        if ( order == null ) {

            return null;
        }

        Long id = order.getId();

        if ( id == null ) {

            return null;
        }

        return id;
    }
}

