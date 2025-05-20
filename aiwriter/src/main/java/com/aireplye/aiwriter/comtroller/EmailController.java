package com.aireplye.aiwriter.comtroller;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aireplye.aiwriter.dto.EmailRequest;
import com.aireplye.aiwriter.service.EmailWriterService;

@RestController
@RequestMapping("/api/email")
@CrossOrigin(origins = "*")
public class EmailController {

    @Autowired
    private EmailWriterService emailWriterService;
    
    // private static final Logger logger = LoggerFactory.getLogger(EmailController.class);

    
    @PostMapping("/generate")
    public ResponseEntity<String> generaeEmail(@RequestBody EmailRequest emailRequest){
        // logger.info("Email API called..");
        // this is the paid service so we are using gemini for now
        // String generatedEmail = emailWriterService.generateEmail(emailRequest.getEmailContent(),emailRequest.getTone());
        String generatedEmail = emailWriterService.generateEmail(emailRequest);
        return ResponseEntity.ok(generatedEmail);
    }
}
