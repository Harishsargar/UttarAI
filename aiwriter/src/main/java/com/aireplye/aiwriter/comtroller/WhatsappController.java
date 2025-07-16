package com.aireplye.aiwriter.comtroller;

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

import com.aireplye.aiwriter.dto.WhatsappRequest;
import com.aireplye.aiwriter.mongoEntity.User;
import com.aireplye.aiwriter.service.UserService;
import com.aireplye.aiwriter.service.WhatsappWriterService;

@RestController
@RequestMapping("/api/secure/whatsapp")
@CrossOrigin(origins = "*")
public class WhatsappController {

    private final UserService userService;

    @Autowired
    private WhatsappWriterService whatsappWriterService;


    WhatsappController(UserService userService) {
        this.userService = userService;
    }
    
    @PostMapping("/generate")
    public ResponseEntity<String> generateWhatsappMessage(@RequestBody WhatsappRequest whatsappRequest){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        System.out.println("whatsapp controller called..");
        User user = userService.getUserByUsername(userDetails.getUsername());
        if(user.getApiCalls()>0){
            String generatedMessage = whatsappWriterService.generate(whatsappRequest);
            user.setApiCalls(user.getApiCalls()-1);
            userService.saveUser(user);
            return ResponseEntity.ok(generatedMessage);
        }else{
            user.setCurrentPlan(null);
            user.setApiCalls(0);
            userService.saveUser(user);
            return new ResponseEntity<>("API limit reached. Please upgrade your plan.",HttpStatus.PAYMENT_REQUIRED);
        }
        
    }
}
