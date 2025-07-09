package com.example.localnews.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class CityDto {
    private String name;

    private String stateName;

    private String stateCode;

    private Integer newsCount;

}
