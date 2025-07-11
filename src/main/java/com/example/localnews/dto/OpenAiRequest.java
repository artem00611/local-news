package com.example.localnews.dto;

import java.util.List;

public record OpenAiRequest(String model, List<Message> messages) {
}
