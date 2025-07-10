package com.example.localnews.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateNewsRequest {
    private String title;
    private String content;
}
