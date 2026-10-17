package com.springai.dockermodel.Controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DockerAiController {
    private ChatClient chatClient;

    DockerAiController(ChatClient.Builder builder) {
        this.chatClient = builder.build();

    }

    @GetMapping("/chat")
    public String bedRockChat(@RequestParam("message") String message) {
        return chatClient.prompt(message).call().content();
    }
}
