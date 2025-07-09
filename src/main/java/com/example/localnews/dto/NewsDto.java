package com.example.localnews.dto;

import com.example.localnews.model.City;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
