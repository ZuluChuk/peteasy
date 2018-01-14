package com.peteasy.store.service.mapper;

import com.peteasy.store.domain.Country;

import com.peteasy.store.service.dto.CountryDTO;

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

public class CountryMapperImpl implements CountryMapper {

    @Override

    public Country toEntity(CountryDTO dto) {

        if ( dto == null ) {

            return null;
        }

        Country country = new Country();

        country.setId( dto.getId() );

        country.setIsoCode( dto.getIsoCode() );

        country.setName( dto.getName() );

        return country;
    }

    @Override

    public CountryDTO toDto(Country entity) {

        if ( entity == null ) {

            return null;
        }

        CountryDTO countryDTO = new CountryDTO();

        countryDTO.setId( entity.getId() );

        countryDTO.setIsoCode( entity.getIsoCode() );

        countryDTO.setName( entity.getName() );

        return countryDTO;
    }

    @Override

    public List<Country> toEntity(List<CountryDTO> dtoList) {

        if ( dtoList == null ) {

            return null;
        }

        List<Country> list = new ArrayList<Country>();

        for ( CountryDTO countryDTO : dtoList ) {

            list.add( toEntity( countryDTO ) );
        }

        return list;
    }

    @Override

    public List<CountryDTO> toDto(List<Country> entityList) {

        if ( entityList == null ) {

            return null;
        }

        List<CountryDTO> list = new ArrayList<CountryDTO>();

        for ( Country country : entityList ) {

            list.add( toDto( country ) );
        }

        return list;
    }
}

