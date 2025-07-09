package com.example.localnews.repository;

import com.example.localnews.model.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityRepo extends JpaRepository<City, Long> {
    Page<City> findByNameContainingIgnoreCase(String name, Pageable pageable);

    Optional<City> findByName(String name);

    Optional<City> findByNameIgnoreCase(String name);
}
