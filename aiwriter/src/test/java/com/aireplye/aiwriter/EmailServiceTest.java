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

            String welcomehtml = "<!DOCTYPE html>\r\n" + //
                                "<html>\r\n" + //
                                "<head>\r\n" + //
                                "  <meta charset=\"UTF-8\">\r\n" + //
                                "  <title>Welcome to Uttar-AI</title>\r\n" + //
                                "</head>\r\n" + //
                                "<body style=\"font-family: Arial, sans-serif; background-color: #f4f6f8; margin: 0; padding: 0;\">\r\n" + //
                                "  <div style=\"max-width: 600px; margin: 30px auto; background-color: #ffffff; padding: 30px; border-radius: 8px; box-shadow: 0 2px 6px rgba(0,0,0,0.1);\">\r\n" + //
                                "    \r\n" + //
                                "    <div style=\"text-align: center; padding-bottom: 20px;\">\r\n" + //
                                "      <h1 style=\"color: rgb(219, 8, 135);\">Welcome to Uttar-AI</h1>\r\n" + //
                                "    </div>\r\n" + //
                                "\r\n" + //
                                "    <div style=\"font-size: 16px; line-height: 1.6; color: #333;\">\r\n" + //
                                "      <p>Hi <strong>${username}</strong>,</p>\r\n" + //
                                "\r\n" + //
                                "      <p>Thank you for registering with <strong>Uttar-AI</strong>, your intelligent assistant for AI-powered replies.</p>\r\n" + //
                                "\r\n" + //
                                "      <p>You are now on our <strong>Free Tier</strong> plan with <strong>25 free API calls</strong> to get started.</p>\r\n" + //
                                "\r\n" + //
                                "      <p>Uttar-AI supports integration with platforms like <strong>Gmail</strong> and <strong>WhatsApp</strong> to enhance your communication workflows.</p>\r\n" + //
                                "\r\n" + //
                                "      <div style=\"text-align: center;\">\r\n" + //
                                "        <a href=\"https://uttar-ai.vercel.app/\" target=\"_blank\" style=\"display: inline-block; margin-top: 20px; padding: 12px 20px; background-color: rgb(219, 8, 135); color: #ffffff; text-decoration: none; border-radius: 6px; font-weight: bold;\">\r\n" + //
                                "          Go to Uttar-AI\r\n" + //
                                "        </a>\r\n" + //
                                "      </div>\r\n" + //
                                "\r\n" + //
                                "      <p style=\"margin-top: 30px;\">If you have any questions or feedback, feel free to reply to this email. We're here to help!</p>\r\n" + //
                                "\r\n" + //
                                "      <p>Warm regards,<br><strong>The Uttar-AI Team</strong></p>\r\n" + //
                                "    </div>\r\n" + //
                                "\r\n" + //
                                "    <div style=\"margin-top: 30px; text-align: center; font-size: 14px; color: #888888;\">\r\n" + //
                                "      <p>This email was sent to you because you registered for an account on Uttar-AI.</p>\r\n" + //
                                "    </div>\r\n" + //
                                "  </div>\r\n" + //
                                "</body>\r\n" + //
                                "</html>\r\n" + //
                                "";


        String planexpiry = "<!DOCTYPE html>\r\n" + //
                        "<html>\r\n" + //
                        "<head>\r\n" + //
                        "  <meta charset=\"UTF-8\">\r\n" + //
                        "  <title>Uttar-AI: Low API Quota Warning</title>\r\n" + //
                        "</head>\r\n" + //
                        "<body style=\"font-family: Arial, sans-serif; background-color: #f4f6f8; margin: 0; padding: 0;\">\r\n" + //
                        "  <div style=\"max-width: 600px; margin: 30px auto; background-color: #ffffff; padding: 30px; border-radius: 8px; box-shadow: 0 2px 6px rgba(0,0,0,0.1);\">\r\n" + //
                        "\r\n" + //
                        "    <div style=\"text-align: center; padding-bottom: 20px;\">\r\n" + //
                        "      <h1 style=\"color: rgb(219, 8, 135);\">⚠️ You're Running Low on API Calls</h1>\r\n" + //
                        "    </div>\r\n" + //
                        "\r\n" + //
                        "    <div style=\"font-size: 16px; line-height: 1.6; color: #333;\">\r\n" + //
                        "      <p>Hi <strong>${username}</strong>,</p>\r\n" + //
                        "\r\n" + //
                        "      <p>We noticed you're down to <strong>${api_calls} API calls</strong> on your <strong>${current_plan}</strong> plan.</p>\r\n" + //
                        "\r\n" + //
                        "      <p>To continue enjoying seamless access to <strong>Uttar-AI</strong> your intelligent reply companion powered by cutting-edge AI you might want to consider upgrading soon.</p>\r\n" + //
                        "\r\n" + //
                        "      <p><strong>Why Upgrade?</strong></p>\r\n" + //
                        "      <ul style=\"padding-left: 20px;\">\r\n" + //
                        "        <li>🚀 Unlock more API calls without interruptions</li>\r\n" + //
                        "        <li>💬 Integrate easily with Gmail, WhatsApp & more</li>\r\n" + //
                        "      </ul>\r\n" + //
                        "\r\n" + //
                        "      <div style=\"text-align: center;\">\r\n" + //
                        "        <a href=\"https://uttar-ai.vercel.app/\" target=\"_blank\" style=\"display: inline-block; margin-top: 20px; padding: 12px 20px; background-color: rgb(219, 8, 135); color: #ffffff; text-decoration: none; border-radius: 6px; font-weight: bold;\">\r\n" + //
                        "          Upgrade Your Plan\r\n" + //
                        "        </a>\r\n" + //
                        "      </div>\r\n" + //
                        "\r\n" + //
                        "      <p style=\"margin-top: 30px;\">Don't let your productivity stop — upgrade now and continue using Uttar-AI without disruption.</p>\r\n" + //
                        "\r\n" + //
                        "      <p>Thank you for being a valued part of our community.<br><strong>— The Uttar-AI Team</strong></p>\r\n" + //
                        "    </div>\r\n" + //
                        "\r\n" + //
                        "    <div style=\"margin-top: 30px; text-align: center; font-size: 14px; color: #888888;\">\r\n" + //
                        "      <p>This is a system-generated reminder from Uttar-AI.</p>\r\n" + //
                        "    </div>\r\n" + //
                        "\r\n" + //
                        "  </div>\r\n" + //
                        "</body>\r\n" + //
                        "</html>\r\n" + //
                        "";
        emailService.sendHtmlMail("harish.212998107@vcet.edu.in", "testing html mail", planexpiry.replace("${username}", "Harish Sargar").replace("${current_plan}", "pro plan"));
                        
    }
}
