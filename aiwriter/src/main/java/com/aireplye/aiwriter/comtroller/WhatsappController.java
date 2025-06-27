package com.aireplye.aiwriter.comtroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aireplye.aiwriter.dto.WhatsappRequest;
import com.aireplye.aiwriter.service.WhatsappWriterService;

@RestController
@RequestMapping("/api/secure/whatsapp")
@CrossOrigin(origins = "*")
public class WhatsappController {

    @Autowired
    private WhatsappWriterService whatsappWriterService;
    
    @PostMapping("/generate")
    public ResponseEntity<String> generateWhatsappMessage(@RequestBody WhatsappRequest whatsappRequest){
        System.out.println("whatsapp controller called..");
        String generatedMessage = whatsappWriterService.generate(whatsappRequest);
        return ResponseEntity.ok(generatedMessage);
    }
}
