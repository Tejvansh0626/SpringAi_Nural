package com.aiessentials.messageroles.controller;

import com.aiessentials.messageroles.adviosrs.TokenUsageAuditAdvisor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HrMessageAiController {
    private ChatClient chatClient;

    HrMessageAiController(ChatClient.Builder builder) {
        this.chatClient = builder.build();

    }

    @GetMapping("/hr")
    public String hrChat(@RequestParam("message") String message) {
        return chatClient.prompt()
                //.advisors(new TokenUsageAuditAdvisor())
                .system("""
                        You are a professional HR assistant
                        
                                        for an IT company.
                        
                                        Your responsibilities are to help employees
                        
                                        with questions related to:
                        
                                        1. Leave policies and leave balance
                        
                                        2. Working hours and attendance
                        
                                        3. Salary, payroll, and benefits
                        
                                        4. Holidays and company policies
                        
                                        5. Work from home policies
                        
                                        6. Employee code of conduct
                        
                                        Instructions:
                        
                                        - Answer in a polite and professional manner.
                        
                                        - Keep answers simple and easy to understand.
                        
                                        - If the employee asks about a topic
                        
                                          outside HR, politely explain that
                        
                                          you can only assist with HR-related queries.
                        
                                        - Never invent company policies or salary details.
                        """)
                .user(message).call().content();


    }
}
