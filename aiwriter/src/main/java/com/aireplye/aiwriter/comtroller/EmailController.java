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
import com.aireplye.aiwriter.helper.MailHtmlHelper;
import com.aireplye.aiwriter.mongoEntity.User;
import com.aireplye.aiwriter.service.EmailService;
import com.aireplye.aiwriter.service.EmailWriterService;
import com.aireplye.aiwriter.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/secure/email")
@CrossOrigin(origins = "*")
public class EmailController {

    @Autowired
    private EmailWriterService emailWriterService;

    @Autowired
    private UserService userService;
    
    @Autowired
    private EmailService emailService;
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
            if(user.getApiCalls()==10){  // sending mail to notify only 10 calls are left
                String html = MailHtmlHelper.planExpiryNotifyMail
                                            .replace("${current_plan}", user.getCurrentPlan())
                                            .replace("${api_calls}", String.valueOf(user.getApiCalls()))
                                            .replace("${username}", user.getName());;
                try {
                    emailService.sendHtmlMail(user.getEmail(), "API Limit Alert: You're Almost Out on Uttar-AI", html);
                } catch (Exception e) {
                    log.error("unable to send mail for low api count", e);
                }
            }
            userService.saveUser(user);
            return ResponseEntity.ok(generatedEmail);
        }else{
            user.setCurrentPlan(null);
            user.setApiCalls(0);
            if(user.getApiCalls()==0){  // sending mail to notify plan expired
                String html = MailHtmlHelper.planExpiryNotifyMail
                                            .replace("${current_plan}", user.getCurrentPlan())
                                            .replace("${api_calls}", String.valueOf(user.getApiCalls()))
                                            .replace("${username}", user.getName());;
                try {
                    emailService.sendHtmlMail(user.getEmail(), "Your Uttar-AI Plan Has Expired - Renew Now to Stay Connected", html);
                } catch (Exception e) {
                    log.error("unable to send plan expiry mail", e);
                }
            }
            userService.saveUser(user);
            return new ResponseEntity<>("API limit reached. Please upgrade your plan.",HttpStatus.PAYMENT_REQUIRED);
        }
        // this is the paid service so we are using gemini for now
        // String generatedEmail = emailWriterService.generateEmail(emailRequest.getEmailContent(),emailRequest.getTone());
        
    }
}
