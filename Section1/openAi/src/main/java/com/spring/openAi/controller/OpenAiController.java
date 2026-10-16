package com.spring.openAi.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class OpenAiController {
    private ChatClient chatClient;

    OpenAiController(ChatClient.Builder builder) {
        this.chatClient = builder.build();

    }

    @GetMapping({"/chat"})
    public String openAiChat(@RequestParam("message") String message) {
        return chatClient.prompt(message).call().content();


    }
}
