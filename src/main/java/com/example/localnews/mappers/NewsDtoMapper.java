package com.example.localnews.mappers;

import com.example.localnews.dto.CityDto;
import com.example.localnews.dto.NewsDto;
import com.example.localnews.model.News;
import org.springframework.stereotype.Component;

@Component
public class NewsDtoMapper {
    public NewsDto toNewsDto(News news) {
        return NewsDto.builder()
                .title(news.getTitle())
                .content(news.getContent())
                .local(news.getLocal())
                .matchedCity(CityDto.builder()
                        .name(news.getMatchedCity().getName())
                        .stateName(news.getMatchedCity().getStateName())
                        .stateCode(news.getMatchedCity().getStateCode())
                        .newsCount(news.getMatchedCity().getNewsCount())
                        .build())
                .build();
    }
}
