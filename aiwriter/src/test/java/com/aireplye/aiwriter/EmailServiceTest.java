package com.aireplye.aiwriter;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.aireplye.aiwriter.service.EmailService;

import jakarta.mail.MessagingException;

@SpringBootTest
public class EmailServiceTest {

    @Autowired
    private EmailService emailService;

    @Test
    void sendMail(){
        emailService.sendEmail("harish.212998107@vcet.edu.in", "testing email", "this is testing email");
    }

    @Test
    void sendHtmlMail() throws MessagingException{
        String html = "<!DOCTYPE html>\r\n" + //
                        "<html>\r\n" + //
                        "<head>\r\n" + //
                        "  <meta charset=\"UTF-8\">\r\n" + //
                        "  <title>Test Email</title>\r\n" + //
                        "</head>\r\n" + //
                        "<body style=\"font-family: Arial, sans-serif; line-height: 1.6; background-color: #f4f4f4; padding: 20px;\">\r\n" + //
                        "  <div style=\"max-width: 600px; margin: auto; background-color: #fff; padding: 20px; border-radius: 8px;\">\r\n" + //
                        "    <h2 style=\"color: #333;\">🚀 Welcome to AIWriter!</h2>\r\n" + //
                        "    <p>Hello there,</p>\r\n" + //
                        "    <p>This is a <strong>test HTML email</strong> sent using <em>Spring Boot JavaMailSender</em>.</p>\r\n" + //
                        "    <p>Here’s a sample list:</p>\r\n" + //
                        "    <ul>\r\n" + //
                        "      <li>✅ Clean HTML formatting</li>\r\n" + //
                        "      <li>✅ Proper MIME structure</li>\r\n" + //
                        "      <li>✅ Inline styling for email clients</li>\r\n" + //
                        "    </ul>\r\n" + //
                        "    <p style=\"margin-top: 20px;\">\r\n" + //
                        "      Best regards,<br>\r\n" + //
                        "      <strong>AIWriter Team</strong>\r\n" + //
                        "    </p>\r\n" + //
                        "    <hr style=\"margin-top: 30px;\">\r\n" + //
                        "    <p style=\"font-size: 12px; color: #888;\">This is a test email. Please do not reply.</p>\r\n" + //
                        "  </div>\r\n" + //
                        "</body>\r\n" + //
                        "</html>\r\n" ;//

        emailService.sendHtmlMail("harish.212998107@vcet.edu.in", "testing html mail", html);
                        
    }
}
