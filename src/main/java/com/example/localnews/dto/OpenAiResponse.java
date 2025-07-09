package com.example.localnews.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class OpenAiResponse {
    private List<Choice> choices;

    @Setter
    @Getter
    public static class Choice {
        private Message message;

    }
}