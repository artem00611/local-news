package com.example.localnews.controller;

import com.example.localnews.dto.CityDto;
import com.example.localnews.repository.CityRepo;
import com.example.localnews.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping("/api/city")
@RequiredArgsConstructor
public class CityController {

    private final CityService cityService;

    @GetMapping("/all")
    public Page<CityDto> getCities(Pageable pageable){
        return cityService.getAll(pageable);
    }

    @GetMapping("/search")
    public Page<CityDto> searchCities(@RequestParam String query, Pageable pageable){
        return cityService.searchCity(query, pageable);
    }


}
