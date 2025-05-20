package com.aireplye.aiwriter.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.aireplye.aiwriter.dto.MessageRequest;
import com.aireplye.aiwriter.dto.WhatsappRequest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class WhatsappWriterService {

    @Autowired
    private WebClient webClient;

    @Value("${gemini.api.key}")
    private String geminiApiKey;
    @Value("${gemini.api.url}")
    private String geminiApiUrl;

    public String generate(WhatsappRequest whatsappRequest) {
        // build promt
        String promt = buildPrompt(whatsappRequest);
        // System.out.println("prompt: " + promt);

        // carft the requeest
        Map<String, Object> RequestBody = Map.of(
                "contents", new Object[] {
                        Map.of("parts", new Object[] {
                                Map.of("text", promt)
                        })
                });

        // do request
        String response = webClient.post()
                .uri(geminiApiUrl + geminiApiKey)
                .header("content-type", "application/json")
                .bodyValue(RequestBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        // return the respone
        // System.out.println("response: " + response);
        return extractRespnceContent(response);
    }

    // public String buildPrompt(WhatsappRequest whatsappRequest){
    // StringBuilder prompt = new StringBuilder();
    // prompt.append("You are an AI that helps generate polite and context-aware
    // WhatsApp replies.\n");
    // prompt.append("Here is the conversation so far:\n");

    // for (MessageRequest msg : whatsappRequest.getConversation()) {
    // prompt.append(msg.getSender()).append(":
    // ").append(msg.getMessage()).append("\n");
    // }

    // prompt.append("Based on the above, generate a reply in a ");
    // prompt.append(whatsappRequest.getTone() != null ? whatsappRequest.getTone() :
    // "neutral");
    // prompt.append(" tone that sounds natural and human-like.\n");
    // return prompt.toString();
    // }
// ===============================================================================================
    // public String buildPrompt(WhatsappRequest whatsappRequest) {
    //     StringBuilder prompt = new StringBuilder();
    //     prompt.append(
    //             "You are an whatsapp replyer, Act as a 'me' from the below conversation and give the appropriate reply to 'them' .\n");
    //     prompt.append("understand the conversation and reply in a minimal way. and try to answer the most recent message of 'them'  \n");
    //     prompt.append("Here is the conversation so far:\n");

    //     for (MessageRequest msg : whatsappRequest.getConversation()) {
    //         prompt.append(msg.getSender()).append(": ").append(msg.getMessage()).append("\n");
    //     }

    //     prompt.append("Based on the above, generate a reply in a ");
    //     prompt.append(whatsappRequest.getTone() != null ? whatsappRequest.getTone() : "neutral");
    //     prompt.append(" tone that sounds natural and similer to 'me'.\n");
    //     prompt.append("generate only the reply, do not add any other text.\n");
    //     return prompt.toString();
    // }
// ==============================================================================================
public String buildPrompt(WhatsappRequest whatsappRequest) {
    StringBuilder prompt = new StringBuilder();
    
    // Core assistant instructions
    prompt.append("You are my personal WhatsApp assistant. Analyze the conversation below and respond as 'me' to the most recent message from 'them'.\n\n");
    
    // Personality guidance
    prompt.append("IMPORTANT GUIDELINES:\n");
    prompt.append("1. Match my communication style, vocabulary, and sentence structure based on my previous messages\n");
    prompt.append("2. Keep responses concise and natural - brief messages are better than lengthy ones\n");
    prompt.append("3. Don't repeat information we both already know\n");
    prompt.append("4. Maintain conversation flow and topic relevance\n");
    prompt.append("5. Respond directly to questions or requests in their latest message\n");
    prompt.append("6. If there's no recent conversation, restart naturally with an engaging topic\n\n");
    
    // Context analysis
    prompt.append("Conversation History:\n");
    
    // Handle empty conversation
    if (whatsappRequest.getConversation() == null || whatsappRequest.getConversation().isEmpty()) {
        prompt.append("No previous messages.\n\n");
        prompt.append("CONTEXT CLUES:\n");
        prompt.append("- This is the start of a new conversation\n");
        prompt.append("- Begin with a friendly, engaging message that feels natural\n");
        prompt.append("- Be extroverted and start with an interesting topic or question\n");
    } else {
        // Process the conversation
        int meMessageCount = 0;
        int themMessageCount = 0;
        String myLastMessage = "";
        String theirLastMessage = "";
        String lastSender = "";
        
        for (MessageRequest msg : whatsappRequest.getConversation()) {
            prompt.append(msg.getSender()).append(": ").append(msg.getMessage()).append("\n");
            lastSender = msg.getSender();
            
            if (msg.getSender().equals("me")) {
                myLastMessage = msg.getMessage();
                meMessageCount++;
            } else {
                theirLastMessage = msg.getMessage();
                themMessageCount++;
            }
        }
        
        // Additional context for the model
        prompt.append("\nCONTEXT CLUES:\n");
        
        // Who was the last person to send a message?
        prompt.append("- The most recent message is from '").append(lastSender).append("'\n");
        
        // Determine if we should respond based on last sender
        if (lastSender.equals("me")) {
            prompt.append("- Since I was the last one to send a message, send a follow-up only if my last message expected a response but didn't get one\n");
        } else {
            prompt.append("- They were the last to send a message, so a response is needed\n");
            
            // Analyze their last message for questions or requests
            if (theirLastMessage.contains("?")) {
                prompt.append("- Their last message contains a question that needs answering\n");
            }
            
            // Check for common conversation starters/requests
            if (theirLastMessage.toLowerCase().contains("how are you") || 
                theirLastMessage.toLowerCase().contains("what's up") ||
                theirLastMessage.toLowerCase().contains("whats up")) {
                prompt.append("- They're asking about my status or wellbeing\n");
            }
        }
        
        // Analyze conversation depth
        prompt.append("- This conversation has " + (meMessageCount + themMessageCount) + " total messages\n");
        
        // Check for conversation stall patterns
        boolean isStalled = false;
        
        // Pattern 1: They asked a question and I didn't respond
        if (lastSender.equals("them") && theirLastMessage.contains("?")) {
            prompt.append("- They asked a question that needs answering\n");
        }
        
        // Pattern 2: Short conversation with few exchanges
        if (meMessageCount <= 1 && themMessageCount <= 2) {
            prompt.append("- This is a new conversation that's just getting started\n");
        }
        
        // Pattern 3: I haven't responded to their message yet
        if (lastSender.equals("them")) {
            prompt.append("- I haven't responded to their last message yet\n");
        }
        
        // If we detected a stalled conversation based on patterns
        if (isStalled) {
            prompt.append("- The conversation appears to have stalled\n");
            prompt.append("- Restart with something engaging and relevant to previous messages\n");
        }
    }
    
    // Tone guidance
    String requestedTone = whatsappRequest.getTone() != null ? whatsappRequest.getTone() : "neutral";
    prompt.append("- Respond in a ").append(requestedTone).append(" tone\n");
    
    // Output instructions
    prompt.append("\nYour response should:\n");
    prompt.append("1. ONLY include the exact message I should send (no quotation marks, prefixes, or explanations)\n");
    prompt.append("2. Be conversational and natural, as if I typed it myself\n");
    prompt.append("3. Match my usual message length and complexity\n");
    
    // Cold start instructions if needed
    if (whatsappRequest.getConversation() == null || whatsappRequest.getConversation().isEmpty()) {
        prompt.append("\nThis is a cold start with no previous messages. Generate a friendly, natural conversation starter that:\n");
        prompt.append("- Is appropriate for the requested tone (").append(requestedTone).append(")\n");
        prompt.append("- Asks an engaging question or shares an interesting update\n");
        prompt.append("- Feels spontaneous and not forced\n");
        prompt.append("- Is something an extroverted person would naturally send\n");
    }
    
    return prompt.toString();
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
}
