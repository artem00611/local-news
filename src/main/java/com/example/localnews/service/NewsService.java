package com.example.localnews.service;

import com.example.localnews.dto.CityDto;
import com.example.localnews.dto.ClassifiedNewsDto;
import com.example.localnews.dto.CreateNewsRequest;
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
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class NewsService {
    private final NewsRepo newsRepo;
    private final CityRepo cityRepo;
    private final NewsClassifierService newsClassifierService;
    private final NewsDtoMapper newsDtoMapper;

    public Page<NewsDto> getNewsByCity(String cityName, String stateName, Pageable pageable){
        City matchedCity = cityRepo.findByNameAndStateCode(cityName, stateName)
                .orElseThrow( () -> new CityNotFoundException("There is no city with such name"));
        System.out.println(matchedCity);
        return newsRepo.findByMatchedCity(matchedCity, pageable).map(newsDtoMapper::toNewsDto);
    }

    public Page<NewsDto> getGlobalNews(Pageable pageable){
        City matchedCity = cityRepo.findByNameAndStateCode("Global", "GL")
                .orElseThrow( () -> new CityNotFoundException("There is no city with such name"));
        System.out.println(matchedCity);
        return newsRepo.findByMatchedCity(matchedCity, pageable).map(newsDtoMapper::toNewsDto);
    }

    @Transactional(rollbackFor = Exception.class)
    public void createNews(CreateNewsRequest createNewsRequest){
        ClassifiedNewsDto newsDto = newsClassifierService.classify(createNewsRequest.getTitle(), createNewsRequest.getContent());
        City city = cityRepo.findByNameAndStateCodeIgnoreCase(newsDto.getCityName(), newsDto.getStateCode())
                .orElseThrow(() -> new CityNotFoundException("There is no city with such name"));

        News result = News.builder()
                .title(createNewsRequest.getTitle())
                .content(createNewsRequest.getContent())
                .matchedCity(city)
                .local(newsDto.getLocal())
                .build();

        newsRepo.save(result);
        city.setNewsCount(city.getNewsCount() + 1);
        cityRepo.save(city);
    }


}
