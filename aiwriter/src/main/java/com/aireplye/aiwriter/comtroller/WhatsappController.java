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
import com.aireplye.aiwriter.helper.MailHtmlHelper;
import com.aireplye.aiwriter.mongoEntity.User;
import com.aireplye.aiwriter.service.EmailService;
import com.aireplye.aiwriter.service.UserService;
import com.aireplye.aiwriter.service.WhatsappWriterService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/secure/whatsapp")
@CrossOrigin(origins = "*")
public class WhatsappController {

    private final UserService userService;

    @Autowired
    private WhatsappWriterService whatsappWriterService;

    @Autowired
    private EmailService emailService;


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
            if(user.getApiCalls()==10){  // sending mail to notify only 10 calls are left
                String html = MailHtmlHelper.planExpiryNotifyMail
                                            .replace("${current_plan}", user.getCurrentPlan())
                                            .replace("${api_calls}", String.valueOf(user.getApiCalls()))
                                            .replace("${username}", user.getName());
                try {
                    emailService.sendHtmlMail(user.getEmail(), "API Limit Alert: You're Almost Out on Uttar-AI", html);
                } catch (Exception e) {
                    log.error("unable to send mail for low api count", e);
                }
            }
            userService.saveUser(user);
            return ResponseEntity.ok(generatedMessage);
        }else{
            // user.setCurrentPlan(null);
            user.setApiCalls(0);
            if(user.getApiCalls()==0){  // sending mail to notify plan expired
                String html = MailHtmlHelper.planExpiryNotifyMail
                                            .replace("${current_plan}", user.getCurrentPlan())
                                            .replace("${api_calls}", String.valueOf(user.getApiCalls()))
                                            .replace("${username}", user.getName());
                try {
                    emailService.sendHtmlMail(user.getEmail(), "Your Uttar-AI Plan Has Expired - Renew Now to Stay Connected", html);
                } catch (Exception e) {
                    log.error("unable to send plan expiry mail", e);
                }
            }
            userService.saveUser(user);
            return new ResponseEntity<>("API limit reached. Please upgrade your plan.",HttpStatus.PAYMENT_REQUIRED);
        }
        
    }
}
