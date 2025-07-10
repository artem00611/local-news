package com.example.localnews.service;

import com.example.localnews.dto.CreateNewsRequest;
import com.example.localnews.model.City;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class NewsImportService {

    private final NewsService newsService;

    public void importNews() {
        String path = "static/news.csv";
        log.info("Importing news data...");

        try (InputStream inputStream = new ClassPathResource(path).getInputStream();
             InputStreamReader isr = new InputStreamReader(inputStream);
             CSVReader reader = new CSVReader(isr)) {

            String[] row;
            reader.readNext(); // skip header

            while ((row = reader.readNext()) != null) {
                if (row.length < 4) {
                    log.warn("Skipping invalid row: {}", (Object) row);
                    continue;
                }

                CreateNewsRequest newsRequest = CreateNewsRequest.builder()
                        .title(row[2])
                        .content(row[3])
                        .build();

                newsService.createNews(newsRequest);
            }

            log.info("News data import complete successfully");
        } catch (IOException e) {
            log.error("Bulk import failed", e);
            throw new IllegalStateException("Import failed", e);
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }

}
