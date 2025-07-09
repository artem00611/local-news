package com.example.localnews.controller;

import com.example.localnews.dto.NewsDto;
import com.example.localnews.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/news")
@RequiredArgsConstructor
public class NewsController {
    private final NewsService newsService;

    @GetMapping("/{cityName}")
    public Page<NewsDto> getNewsByCity(@PathVariable String cityName, Pageable pageable){
        return newsService.getNewsByCity(cityName, pageable);
    }

    @PostMapping
    public void createNews(@RequestParam String title, @RequestParam String content){
        newsService.createNews(title,content);
    }
}
