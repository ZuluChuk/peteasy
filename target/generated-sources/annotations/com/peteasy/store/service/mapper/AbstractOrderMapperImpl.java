package com.peteasy.store.service.mapper;

import com.peteasy.store.domain.AbstractOrder;

import com.peteasy.store.domain.Address;

import com.peteasy.store.service.dto.AbstractOrderDTO;

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

public class AbstractOrderMapperImpl implements AbstractOrderMapper {

    @Autowired

    private AddressMapper addressMapper;

    @Override

    public List<AbstractOrder> toEntity(List<AbstractOrderDTO> dtoList) {

        if ( dtoList == null ) {

            return null;
        }

        List<AbstractOrder> list = new ArrayList<AbstractOrder>();

        for ( AbstractOrderDTO abstractOrderDTO : dtoList ) {

            list.add( toEntity( abstractOrderDTO ) );
        }

        return list;
    }

    @Override

    public List<AbstractOrderDTO> toDto(List<AbstractOrder> entityList) {

        if ( entityList == null ) {

            return null;
        }

        List<AbstractOrderDTO> list = new ArrayList<AbstractOrderDTO>();

        for ( AbstractOrder abstractOrder : entityList ) {

            list.add( toDto( abstractOrder ) );
        }

        return list;
    }

    @Override

    public AbstractOrderDTO toDto(AbstractOrder abstractOrder) {

        if ( abstractOrder == null ) {

            return null;
        }

        AbstractOrderDTO abstractOrderDTO_ = new AbstractOrderDTO();

        abstractOrderDTO_.setShippingAddressId( abstractOrderShippingAddressId( abstractOrder ) );

        abstractOrderDTO_.setBillingAddressId( abstractOrderBillingAddressId( abstractOrder ) );

        abstractOrderDTO_.setId( abstractOrder.getId() );

        abstractOrderDTO_.setSubTotal( abstractOrder.getSubTotal() );

        abstractOrderDTO_.setTotal( abstractOrder.getTotal() );

        abstractOrderDTO_.setTax( abstractOrder.getTax() );

        abstractOrderDTO_.setShippingCost( abstractOrder.getShippingCost() );

        return abstractOrderDTO_;
    }

    @Override

    public AbstractOrder toEntity(AbstractOrderDTO abstractOrderDTO) {

        if ( abstractOrderDTO == null ) {

            return null;
        }

        AbstractOrder abstractOrder_ = new AbstractOrder();

        abstractOrder_.setShippingAddress( addressMapper.fromId( abstractOrderDTO.getShippingAddressId() ) );

        abstractOrder_.setBillingAddress( addressMapper.fromId( abstractOrderDTO.getBillingAddressId() ) );

        abstractOrder_.setId( abstractOrderDTO.getId() );

        abstractOrder_.setSubTotal( abstractOrderDTO.getSubTotal() );

        abstractOrder_.setTotal( abstractOrderDTO.getTotal() );

        abstractOrder_.setTax( abstractOrderDTO.getTax() );

        abstractOrder_.setShippingCost( abstractOrderDTO.getShippingCost() );

        return abstractOrder_;
    }

    private Long abstractOrderShippingAddressId(AbstractOrder abstractOrder) {

        if ( abstractOrder == null ) {

            return null;
        }

        Address shippingAddress = abstractOrder.getShippingAddress();

        if ( shippingAddress == null ) {

            return null;
        }

        Long id = shippingAddress.getId();

        if ( id == null ) {

            return null;
        }

        return id;
    }

    private Long abstractOrderBillingAddressId(AbstractOrder abstractOrder) {

        if ( abstractOrder == null ) {

            return null;
        }

        Address billingAddress = abstractOrder.getBillingAddress();

        if ( billingAddress == null ) {

            return null;
        }

        Long id = billingAddress.getId();

        if ( id == null ) {

            return null;
        }

        return id;
    }
}

