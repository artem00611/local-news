package com.example.localnews.dto;

import lombok.Data;

@Data
public class ClassifiedNewsDto {
    private Boolean local;
    private String cityName;
    private String stateCode;
}
