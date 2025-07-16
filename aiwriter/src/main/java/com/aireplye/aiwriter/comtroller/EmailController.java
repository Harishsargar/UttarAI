package com.aireplye.aiwriter.comtroller;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aireplye.aiwriter.dto.EmailRequest;
import com.aireplye.aiwriter.mongoEntity.User;
import com.aireplye.aiwriter.service.EmailWriterService;
import com.aireplye.aiwriter.service.UserService;

@RestController
@RequestMapping("/api/secure/email")
@CrossOrigin(origins = "*")
public class EmailController {

    @Autowired
    private EmailWriterService emailWriterService;

    @Autowired
    private UserService userService;
    
    // private static final Logger logger = LoggerFactory.getLogger(EmailController.class);

    
    @PostMapping("/generate")
    public ResponseEntity<String> generaeEmail(@RequestBody EmailRequest emailRequest){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        // logger.info("Email API called..");
         User user = userService.getUserByUsername(userDetails.getUsername());
        if(user.getApiCalls()>0){
            String generatedEmail = emailWriterService.generateEmail(emailRequest);
            user.setApiCalls(user.getApiCalls()-1);
            userService.saveUser(user);
            return ResponseEntity.ok(generatedEmail);
        }else{
            user.setCurrentPlan(null);
            user.setApiCalls(0);
            userService.saveUser(user);
            return new ResponseEntity<>("API limit reached. Please upgrade your plan.",HttpStatus.PAYMENT_REQUIRED);
        }
        // this is the paid service so we are using gemini for now
        // String generatedEmail = emailWriterService.generateEmail(emailRequest.getEmailContent(),emailRequest.getTone());
        
    }
}
