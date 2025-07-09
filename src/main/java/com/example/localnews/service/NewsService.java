package com.example.localnews.service;

import com.example.localnews.dto.CityDto;
import com.example.localnews.dto.ClassifiedNewsDto;
import com.example.localnews.dto.NewsDto;
import com.example.localnews.exceptions.CityNotFoundException;
import com.example.localnews.mappers.CityDtoMapper;
import com.example.localnews.mappers.NewsDtoMapper;
import com.example.localnews.model.City;
import com.example.localnews.model.News;
import com.example.localnews.repository.CityRepo;
import com.example.localnews.repository.NewsRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class NewsService {
    private final NewsRepo newsRepo;
    private final CityRepo cityRepo;
    private final NewsClassifierService newsClassifierService;
    private final NewsDtoMapper newsDtoMapper;

    public Page<NewsDto> getNewsByCity(String city, Pageable pageable){
        City matchedCity = cityRepo.findByName(city)
                .orElseThrow( () -> new CityNotFoundException("There is no city with such name"));
        return newsRepo.findByMatchedCityIgnoreCase(matchedCity, pageable).map(newsDtoMapper::toNewsDto);
    }

    public void createNews(String title, String content){
        ClassifiedNewsDto newsDto = newsClassifierService.classify(title,content);
        City city = cityRepo.findByNameIgnoreCase(newsDto.getCity())
                .orElseThrow(() -> new CityNotFoundException("There is no city with such name"));
        News result = News.builder().title(title).content(content).matchedCity(city).local(newsDto.getLocal()).build();

        newsRepo.save(result);
    }


}
