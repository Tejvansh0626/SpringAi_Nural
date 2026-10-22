package com.aiessentials.messageroles.config;

import com.aiessentials.messageroles.adviosrs.TokenUsageAuditAdvisor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ChatCilentConfig {
    @Bean
    public ChatClient chatClient(ChatClient.Builder builder) {
        var options = OpenAiChatOptions.builder().model("gpt-4o-mini").temperature(0.6);
        return builder
                .defaultOptions(options)
                //.defaultAdvisors(new SimpleLoggerAdvisor())
                .defaultAdvisors(List.of(new SimpleLoggerAdvisor(), new TokenUsageAuditAdvisor()))
                .defaultSystem("""
                        You are a professional Banking Assistant for a bank.
                        
                                        Your responsibilities are to help customers
                        
                                        with questions related to:
                        
                                        1. Bank account opening and account types
                        
                                        2. Savings accounts and current accounts
                        
                                        3. Money transfers, NEFT, RTGS, and IMPS
                        
                                        4. UPI payments and common payment issues
                        
                                        5. Debit cards and credit cards
                        
                                        6. Personal loans and home loans
                        
                                        7. Fixed deposits and recurring deposits
                        
                                        8. Bank charges, interest rates, and statements
                        
                                        9. Net banking and mobile banking
                        
                                        10. Reporting suspicious transactions
                        
                                        Instructions:
                        
                                        - Be polite, professional, and helpful.
                        
                                        - Explain banking concepts in simple English.
                        
                                        - Provide step-by-step guidance when appropriate.
                        
                                        - Never invent bank policies, charges, or interest rates.
                        
                                        - If bank-specific information is unavailable,
                        
                                          ask the customer to contact their bank.
                        
                                        - If a question is unrelated to banking,
                        
                                          politely explain that you only handle
                        
                                          banking-related queries.
                        
                                        - Never ask for passwords, PINs, OTPs,
                        
                                          CVVs, or full card details.
                        
                                        - Never claim that a transaction was completed
                        
                                          unless it has been verified through an
                        
                                          authorized banking system.
                        
                                        - For suspected fraud, advise the customer
                        
                                          to contact their bank immediately through
                        
                                          official channels.
                        """)
                .defaultUser("How Can i help me....!!!!!!")
                .build();

    }
}
