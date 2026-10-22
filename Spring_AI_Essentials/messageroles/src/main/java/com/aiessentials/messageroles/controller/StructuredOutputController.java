package com.aiessentials.messageroles.controller;

import com.aiessentials.messageroles.CountryCities;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.converter.ListOutputConverter;
import org.springframework.ai.converter.MapOutputConverter;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class StructuredOutputController {

    private ChatClient chatClient;

    StructuredOutputController(ChatClient.Builder builder) {
        this.chatClient = builder
                .defaultAdvisors(new SimpleLoggerAdvisor()).build();

    }

    // return Response Coverter into pojio object
    @GetMapping("/chat-bean")
    public ResponseEntity<CountryCities> chatBean(@RequestParam("message") String message) {
        CountryCities countryCities = chatClient.prompt().user(message).call().entity(CountryCities.class);
        return ResponseEntity.ok(countryCities);
    }

    // return Llm Response into List object formate
    @GetMapping("/chat-list")
    public ResponseEntity<List<String>> chatList(@RequestParam("message") String message) {
        List<String> cities = chatClient.prompt()
                .user("Return a JSON array of 10 major US city names only, no explanations.")
                .call().entity(new ListOutputConverter());
        return ResponseEntity.ok(cities);

    }
    // return LLM Response into Map Object

    @GetMapping("/chat-map")
    public ResponseEntity<Map<String, Object>> ChatMap(@RequestParam("message") String message) {
        Map<String, Object> mapCities = chatClient.prompt()
                .user(message)
                .call()
                .entity(new MapOutputConverter());
        return ResponseEntity.ok(mapCities);
    }

    @GetMapping("/chat-bean-list")
    public ResponseEntity<List<CountryCities>> chatBeanList(@RequestParam("message") String message) {
        List<CountryCities> cities = chatClient.prompt()
                .user(message)
                .call().entity(new ParameterizedTypeReference<List<CountryCities>>() {
                });
        return ResponseEntity.ok(cities);

    }
}
