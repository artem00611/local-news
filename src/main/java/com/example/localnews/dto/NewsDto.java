package com.example.localnews.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NewsDto {
    private String title;

    private String content;

    private Boolean local;

    private CityDto matchedCity;
}
