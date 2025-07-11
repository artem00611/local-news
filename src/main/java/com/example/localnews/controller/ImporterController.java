package com.example.localnews.controller;

import com.example.localnews.service.NewsImportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/importer")
@RequiredArgsConstructor
public class ImporterController {

    private final NewsImportService newsImportService;

    @PostMapping("/importNews")
    public void importNewsFromFile() {
        newsImportService.importNews();
    }
}
