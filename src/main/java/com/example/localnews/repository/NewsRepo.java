package com.example.localnews.repository;

import com.example.localnews.model.City;
import com.example.localnews.model.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepo extends JpaRepository<News, Long> {
    Page<News> findByMatchedCityIgnoreCase(City matchedCity, Pageable pageable);

    Page<News> findByMatchedCity(City matchedCity, Pageable pageable);
}
