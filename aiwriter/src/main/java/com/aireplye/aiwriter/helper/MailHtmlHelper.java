package com.aireplye.aiwriter.helper;

public class MailHtmlHelper {

    public static String welcomehtmlMail = "<!DOCTYPE html>\r\n" + //
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



    public static String planExpiryNotifyMail = "<!DOCTYPE html>\r\n" + //
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
                        "      <p>To continue enjoying seamless access to <strong>Uttar-AI</strong> your intelligent reply companion powered by cutting-edge AI. you might want to consider upgrading soon.</p>\r\n" + //
                        "\r\n" + //
                        "      <p><strong>Why Upgrade?</strong></p>\r\n" + //
                        "      <ul style=\"padding-left: 20px;\">\r\n" + //
                        "        <li>🚀 Unlock more API calls without interruptions</li>\r\n" + //
                        "        <li>💬 Integrate easily with Gmail, WhatsApp & more</li>\r\n" + //
                        "      </ul>\r\n" + //
                        "<h2 style=\"font-weight: bolder; color:rgb(235, 0, 70);\"> All payments are in test mode. No real Money is Involved </h2>\r\n" + //
                        "      <div style=\"text-align: center;\">\r\n" + //
                        "        <a href=\"https://uttar-ai.vercel.app/\" target=\"_blank\" style=\"display: inline-block; margin-top: 20px; padding: 12px 20px; background-color: rgb(219, 8, 135); color: #ffffff; text-decoration: none; border-radius: 6px; font-weight: bold;\">\r\n" + //
                        "          Upgrade Your Plan\r\n" + //
                        "        </a>\r\n" + //
                        "      </div>\r\n" + //
                        "\r\n" + //
                        "      <p style=\"margin-top: 30px;\">Don't let your productivity stop, upgrade now and continue using Uttar-AI without disruption.</p>\r\n" + //
                        "\r\n" + //
                        "      <p>Thank you for being a valued part of our community.<br><strong>The Uttar-AI Team</strong></p>\r\n" + //
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

    public static String planpurchased = "<!DOCTYPE html>\r\n" + //
                "<html>\r\n" + //
                "<head>\r\n" + //
                "  <meta charset=\"UTF-8\">\r\n" + //
                "  <title>Payment Confirmation - Uttar-AI</title>\r\n" + //
                "</head>\r\n" + //
                "<body style=\"font-family: Arial, sans-serif; background-color: #f4f6f8; margin: 0; padding: 0;\">\r\n" + //
                "  <div style=\"max-width: 600px; margin: 30px auto; background-color: #ffffff; padding: 30px; border-radius: 8px; box-shadow: 0 2px 6px rgba(0,0,0,0.1);\">\r\n" + //
                "    \r\n" + //
                "    <div style=\"text-align: center; padding-bottom: 20px;\">\r\n" + //
                "      <h1 style=\"color: rgb(219, 8, 135);\">Payment Successful 🎉</h1>\r\n" + //
                "    </div>\r\n" + //
                "\r\n" + //
                "    <div style=\"font-size: 16px; line-height: 1.6; color: #333;\">\r\n" + //
                "      <p>Hi <strong>${username}</strong>,</p>\r\n" + //
                "\r\n" + //
                "      <p>Thank you for your purchase! We're excited to confirm that your payment for the <strong>${plan_purchased}</strong> has been successfully processed.</p>\r\n" + //
                "\r\n" + //
                "      <h3 style=\"margin-top: 20px; color: #fa269a;\">Payment Details:</h3>\r\n" + //
                "      <ul style=\"list-style-type: none; padding-left: 0;\">\r\n" + //
                "        <li><strong>Plan:</strong> ${plan_purchased}</li>\r\n" + //
                "        <li><strong>API Calls Available:</strong> ${api_call_left}</li>\r\n" + //
                "        <li><strong>Amount Paid:</strong> ₹${amount_paid}</li>\r\n" + //
                "        <li><strong>Transaction ID:</strong> ${transaction_id}</li>\r\n" + //
                "      </ul>\r\n" + //
                "\r\n" + //
                "      <p>You can now continue using all premium features of Uttar-AI without interruption.</p>\r\n" + //
                "<h2 style=\"font-weight: bolder; color:rgb(235, 0, 70);\"> All payments are in test mode. No real Money is Involved </h2>\r\n" + //
                "      <div style=\"text-align: center;\">\r\n" + //
                "        <a href=\"https://uttar-ai.vercel.app/\" target=\"_blank\" style=\"display: inline-block; margin-top: 25px; padding: 12px 20px; background-color: rgb(219, 8, 135); color: #ffffff; text-decoration: none; border-radius: 6px; font-weight: bold;\">\r\n" + //
                "          Go to Dashboard\r\n" + //
                "        </a>\r\n" + //
                "      </div>\r\n" + //
                "\r\n" + //
                "      <p style=\"margin-top: 30px;\">If you have any questions about this transaction, feel free to reach out to us by replying to this email.</p>\r\n" + //
                "\r\n" + //
                "      <p>Warm regards,<br><strong>The Uttar-AI Team</strong></p>\r\n" + //
                "    </div>\r\n" + //
                "\r\n" + //
                "    <div style=\"margin-top: 30px; text-align: center; font-size: 14px; color: #888888;\">\r\n" + //
                "      <p>This email serves as an official confirmation of your payment on Uttar-AI.</p>\r\n" + //
                "    </div>\r\n" + //
                "  </div>\r\n" + //
                "</body>\r\n" + //
                "</html>\r\n" + //
                "";

    public static String paymentFailed = "<!DOCTYPE html>\r\n" + //
                "<html>\r\n" + //
                "<head>\r\n" + //
                "  <meta charset=\"UTF-8\">\r\n" + //
                "  <title>Payment Failed - Uttar-AI</title>\r\n" + //
                "</head>\r\n" + //
                "<body style=\"font-family: Arial, sans-serif; background-color: #f4f6f8; margin: 0; padding: 0;\">\r\n" + //
                "  <div style=\"max-width: 600px; margin: 30px auto; background-color: #ffffff; padding: 30px; border-radius: 8px; box-shadow: 0 2px 6px rgba(0,0,0,0.1);\">\r\n" + //
                "\r\n" + //
                "    <div style=\"text-align: center; padding-bottom: 20px;\">\r\n" + //
                "      <h1 style=\"color: #e63946;\">Payment Failed</h1>\r\n" + //
                "    </div>\r\n" + //
                "\r\n" + //
                "    <div style=\"font-size: 16px; line-height: 1.6; color: #333;\">\r\n" + //
                "      <p>Hi <strong>${username}</strong>,</p>\r\n" + //
                "\r\n" + //
                "      <p>We regret to inform you that your recent payment attempt for the <strong>${plan_purchased}</strong> plan on <strong>Uttar-AI</strong> was unsuccessful.</p>\r\n" + //
                "\r\n" + //
                "      <p><strong>Transaction ID:</strong> ${transaction_id}</p>\r\n" + //
                "\r\n" + //
                "      <p>If any amount was debited from your bank account or wallet, it will be automatically refunded within <strong>3–4 business days</strong>.</p>\r\n" + //
                "\r\n" + //
                "      <p>To continue enjoying premium access, please retry the payment at your convenience.</p>\r\n" + //
                "<h2 style=\"font-weight: bolder; color:rgb(235, 0, 70);\"> All payments are in test mode. No real Money is Involved </h2>\r\n" + //
                "      <div style=\"text-align: center;\">\r\n" + //
                "        <a href=\"https://uttar-ai.vercel.app/pricing\" target=\"_blank\" style=\"display: inline-block; margin-top: 20px; padding: 12px 20px; background-color: #e63946; color: #ffffff; text-decoration: none; border-radius: 6px; font-weight: bold;\">\r\n" + //
                "          Retry Payment\r\n" + //
                "        </a>\r\n" + //
                "      </div>\r\n" + //
                "\r\n" + //
                "      <p style=\"margin-top: 30px;\">If the problem persists or if you need assistance, please contact our support team. We're here to help!</p>\r\n" + //
                "\r\n" + //
                "      <p>Warm regards,<br><strong>The Uttar-AI Team</strong></p>\r\n" + //
                "    </div>\r\n" + //
                "\r\n" + //
                "    <div style=\"margin-top: 30px; text-align: center; font-size: 14px; color: #888888;\">\r\n" + //
                "      <p>This email was sent to you because a payment attempt was made on Uttar-AI.</p>\r\n" + //
                "    </div>\r\n" + //
                "  </div>\r\n" + //
                "</body>\r\n" + //
                "</html>\r\n" + //
                "";
}
