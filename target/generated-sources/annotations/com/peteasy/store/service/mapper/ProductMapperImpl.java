package com.peteasy.store.service.mapper;

import com.peteasy.store.domain.Product;

import com.peteasy.store.service.dto.ProductDTO;

import java.util.ArrayList;

import java.util.Arrays;

import java.util.List;

import javax.annotation.Generated;

import org.springframework.stereotype.Component;

@Generated(

    value = "org.mapstruct.ap.MappingProcessor",

    date = "2017-09-07T20:47:29+0100",

    comments = "version: 1.1.0.Final, compiler: javac, environment: Java 1.8.0_144 (Oracle Corporation)"

)

@Component

public class ProductMapperImpl implements ProductMapper {

    @Override

    public Product toEntity(ProductDTO dto) {

        if ( dto == null ) {

            return null;
        }

        Product product = new Product();

        product.setId( dto.getId() );

        product.setImage( dto.getImage() );

        if ( dto.getDescription() != null ) {

            byte[] description = dto.getDescription();

            product.setDescription( Arrays.copyOf( description, description.length ) );
        }

        product.setDescriptionContentType( dto.getDescriptionContentType() );

        product.setPrice( dto.getPrice() );

        return product;
    }

    @Override

    public ProductDTO toDto(Product entity) {

        if ( entity == null ) {

            return null;
        }

        ProductDTO productDTO = new ProductDTO();

        productDTO.setId( entity.getId() );

        productDTO.setImage( entity.getImage() );

        if ( entity.getDescription() != null ) {

            byte[] description = entity.getDescription();

            productDTO.setDescription( Arrays.copyOf( description, description.length ) );
        }

        productDTO.setDescriptionContentType( entity.getDescriptionContentType() );

        productDTO.setPrice( entity.getPrice() );

        return productDTO;
    }

    @Override

    public List<Product> toEntity(List<ProductDTO> dtoList) {

        if ( dtoList == null ) {

            return null;
        }

        List<Product> list = new ArrayList<Product>();

        for ( ProductDTO productDTO : dtoList ) {

            list.add( toEntity( productDTO ) );
        }

        return list;
    }

    @Override

    public List<ProductDTO> toDto(List<Product> entityList) {

        if ( entityList == null ) {

            return null;
        }

        List<ProductDTO> list = new ArrayList<ProductDTO>();

        for ( Product product : entityList ) {

            list.add( toDto( product ) );
        }

        return list;
    }
}

