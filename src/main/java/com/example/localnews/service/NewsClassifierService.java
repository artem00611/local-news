package com.example.localnews.service;

import com.example.localnews.dto.ClassifiedNewsDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class NewsClassifierService {
    private final OpenAiClient openAiClient;

    public ClassifiedNewsDto classify(String title, String content) {
        String prompt = """
                You are a strict JSON generator.
                Given a news article, respond with only a single‑line JSON object (no markdown, no code fences, no extra keys).
                
                Return exactly:
                {
                  "local": "true" | "false",
                  "cityName": "<city name or \\"Global\\">",
                  "stateCode": "<state code or \\"GL\\">"
                }
                
                Rules:
                1. If local is "false", set cityName to "Global" and stateCode to "GL".
                2. If local is "true", set cityName and stateCode to the appropriate US city and state.
                3. If local is "true" but you cannot determine the stateCode, choose the first US stateCode that has this city in it.
                4. Do not output anything else.
                
                Article:
                Title: %s
                Content: %s
                """.formatted(title, content);

        String response = openAiClient.classifyNews(prompt);

        log.info("RESPONSE: " + response);

        String cleanJson = response
                .replaceAll("(?i)^```json\\s*", "") // remove starting ```json (case-insensitive)
                .replaceAll("(?i)^```\\s*", "")     // remove starting ``` if no json
                .replaceAll("\\s*```$", "")         // remove ending ```
                .trim();

        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.readValue(cleanJson, ClassifiedNewsDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Invalid GPT response: " + cleanJson + "  " + e);
        }
    }
}
