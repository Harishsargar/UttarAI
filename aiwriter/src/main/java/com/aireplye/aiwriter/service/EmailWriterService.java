package com.aireplye.aiwriter.service;

import java.util.Map;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.aireplye.aiwriter.dto.EmailRequest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class EmailWriterService {
    
    @Autowired
    private ChatModel chatModel;

    @Autowired
    private WebClient webClient;

    @Value("${gemini.api.key}")
    private String geminiApiKey;
    @Value("${gemini.api.url}")
    private String geminiApiUrl;

    // paid service
    public String generateEmail(String context, String tone){
        System.out.println("calling openai");
        String prompt = "Write an email in a " + tone + " tone. " + context;
        String openaiResponce = chatModel.call(prompt);     //using openai is used
        System.out.println("openai response: " + openaiResponce);
        return openaiResponce;
    }

    public String generateEmail(EmailRequest emailRequest){
        //build promt
        String promt  = buildPrompt(emailRequest);
        //carft the request
        Map<String, Object> RequestBody = Map.of(
            "contents", new Object[]{
                Map.of("parts", new Object[]{
                    Map.of("text", promt)
                })
            } 
        );
        //do request
        String response = webClient.post()
            .uri(geminiApiUrl + geminiApiKey)
            .header("content-type", "application/json")
            .bodyValue(RequestBody)
            .retrieve()
            .bodyToMono(String.class)
            .block();
        //return the responce

            System.out.println(response);
        return extractRespnceContent(response);

    }


    public String extractRespnceContent(String response) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(response);
            return jsonNode.path("candidates")
                           .get(0)
                           .path("content")
                           .path("parts")
                           .get(0)
                           .path("text")
                           .asText();
        } catch (Exception e) {
            return "Error processing the response: " + e.getMessage();
        }
        
    }

    public String buildPrompt(EmailRequest emailRequest) {
        StringBuilder prompt = new StringBuilder();
        prompt.append("You are an AI assistant that writes professional email replies.\n");
        prompt.append("Based on the email content provided below, generate a clear, contextually appropriate reply.\n");
        prompt.append("Use a " + emailRequest.getTone() + " tone in the response.\n");
        prompt.append("most important rule: Do not include the subject line in your reply.\n");
        prompt.append("Only provide the email body as the response.\n");
        prompt.append("Here is the original email content:\n");
        prompt.append(emailRequest.getEmailContent());
        return prompt.toString();
    }

}
