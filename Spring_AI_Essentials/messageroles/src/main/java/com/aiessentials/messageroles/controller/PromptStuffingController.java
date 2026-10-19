package com.aiessentials.messageroles.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PromptStuffingController {
    private ChatClient chatClient;
    @Value("classpath:/promptTemplates/systemPropmptStuffing.st")
    Resource systemPropmptStuffing;

    PromptStuffingController(ChatClient chatClient) {
        this.chatClient = chatClient;

    }

    @GetMapping("/promptStuffing")
    public String promptSysStuffing(@RequestParam("message") String message) {
        return chatClient.prompt()
                //.system(systemPropmptStuffing)
                .user(message).call().content();

    }
}
