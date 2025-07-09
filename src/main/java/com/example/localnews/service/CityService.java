package com.example.localnews.service;

import com.example.localnews.dto.CityDto;
import com.example.localnews.mappers.CityDtoMapper;
import com.example.localnews.model.City;
import com.example.localnews.repository.CityRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CityService {

    private final CityRepo cityRepo;
    private final CityDtoMapper cityDtoMapper;

    public Page<CityDto> getAll(Pageable pageable){
        return cityRepo.findAll(pageable).map(cityDtoMapper::toCityDto);
    }

    public Page<CityDto> searchCity(String query, Pageable pageable){
        return cityRepo.findByNameContainingIgnoreCase(query, pageable).map(cityDtoMapper::toCityDto);
    }



}
