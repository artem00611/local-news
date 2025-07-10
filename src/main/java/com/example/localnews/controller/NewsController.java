package com.example.localnews.controller;

import com.example.localnews.dto.CreateNewsRequest;
import com.example.localnews.dto.NewsDto;
import com.example.localnews.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/news")
@RequiredArgsConstructor
public class NewsController {
    private final NewsService newsService;

    @GetMapping
    public Page<NewsDto> getNewsByCity(@RequestParam String cityName, @RequestParam String stateName, Pageable pageable){
        return newsService.getNewsByCity(cityName, stateName, pageable);
    }

    @PostMapping
    public void createNews(@RequestBody CreateNewsRequest createNewsRequest){
        newsService.createNews(createNewsRequest);
    }

    @GetMapping("/global")
    public Page<NewsDto> getGlobalNews(Pageable pageable){
        return newsService.getGlobalNews(pageable);
    }
}
