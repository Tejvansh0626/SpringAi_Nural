package com.aiessentials.messageroles.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DefaultsController {
    private ChatClient chatClient;

    DefaultsController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/defaultsChat")
    public String defaultsChat(@RequestParam("message") String message) {
        return chatClient
                .prompt()
                .system("""
                         1. Cheque issuance and deposit process
                                2. Cheque bounce issues and reasons
                                3. Cheque signature updates and verification
                                4. Cheque book requests and replacements
                                5. Stop payment requests for cheques
                                6. Clearing timelines and cheque tracking
                                7. Charges related to cheque transactions
                                8. Reporting lost or stolen cheques
                                9. General cheque-related policies and procedures
                        """)
                .user(message)
                .call()
                .content();

    }
}
