package com.aireplye.aiwriter.dto;

import java.util.List;

import lombok.Data;

@Data
public class WhatsappRequest {
    private List<MessageRequest> conversation;
    private String tone;
}


