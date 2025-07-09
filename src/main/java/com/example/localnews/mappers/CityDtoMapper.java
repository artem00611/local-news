package com.example.localnews.mappers;

import com.example.localnews.dto.CityDto;
import com.example.localnews.model.City;
import org.springframework.stereotype.Component;

@Component
public class CityDtoMapper {
    public CityDto toCityDto(City city){
        return CityDto.builder()
                .name(city.getName())
                .stateName(city.getName())
                .stateCode(city.getStateCode())
                .newsCount(city.getNewsCount())
                .build();
    }
}
