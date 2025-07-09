package com.example.localnews.service;

import com.example.localnews.dto.ClassifiedNewsDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NewsClassifierService {
    private final OpenAiClient openAiClient;

    public ClassifiedNewsDto classify(String title, String content) {
        String prompt = """
                  Classify the following news article. Return JSON:
                        {
                        "local": "true" or "false",
                        "city": "City" or hardcode it to "Global"
                        }
                
                        Title: %s
                        Content: %s
                """.formatted(title,content);

        String response = openAiClient.classifyNews(prompt);

        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.readValue(response, ClassifiedNewsDto.class);
        } catch (JsonProcessingException e){
            throw new RuntimeException("Invalid GPT response: " + response + "  " + e);
        }
    }
}
