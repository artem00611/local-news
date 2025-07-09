package com.example.localnews.component;

import com.example.localnews.model.City;
import com.example.localnews.repository.CityRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
public class CityImporter implements CommandLineRunner {

    private final CityRepo cityRepo;

    @Autowired
    public CityImporter(CityRepo cityRepo){
        this.cityRepo = cityRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        if (cityRepo.count() > 0) {
            log.info("Cities already imported. Skipping import.");
            return;
        }

        log.info("Importing city data...");
        cityRepo.deleteAll();
        InputStream inputStream = getClass().getResourceAsStream("/static/uscities.csv");
        BufferedReader reader = null;
        if (inputStream != null) {
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while (reader.readLine() != null) {
                line = reader.readLine();
                List<String> parts = Arrays.stream(line.split(",")).map(x -> x.replace("\"", "")).toList();
                City city = new City();
                city.setName(parts.get(0));
                city.setStateCode(parts.get(2));
                city.setStateName(parts.get(3));
                cityRepo.save(city);
            }
        }
        log.info("City data import complete successfully");

    }
}
