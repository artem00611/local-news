package com.example.localnews.service;

import com.example.localnews.dto.Message;
import com.example.localnews.dto.OpenAiRequest;
import com.example.localnews.dto.OpenAiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OpenAiClient {

    private final WebClient webClient;

    private String model = "gpt-4o";

    public String classifyNews(String prompt){
        OpenAiRequest request = new OpenAiRequest(model, List.of(new Message("user", prompt)));

    return webClient.post()
            .uri("/chat/completions")
            .bodyValue(request)
            .retrieve()
            .bodyToMono(OpenAiResponse.class)
            .map(openAiResponse -> openAiResponse.getChoices().get(0).getMessage().content())
            .block();
    }
}
