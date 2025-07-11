package com.example.localnews.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CityDto {
    private String name;

    private String stateName;

    private String stateCode;

    private Integer newsCount;

}
