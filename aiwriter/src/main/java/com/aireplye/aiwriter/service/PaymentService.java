package com.aireplye.aiwriter.service;

import java.security.Principal;
import java.util.UUID;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.aireplye.aiwriter.dto.CurrentPlan;
import com.aireplye.aiwriter.dto.OrderRequestDTO;
import com.aireplye.aiwriter.dto.PaymentDetailsDTO;
import com.aireplye.aiwriter.helper.MailHtmlHelper;
import com.aireplye.aiwriter.mongoEntity.RazorpayOrder;
import com.aireplye.aiwriter.mongoEntity.User;
import com.aireplye.aiwriter.mongoRepo.RazorpayOrderRepo;
import com.aireplye.aiwriter.mongoRepo.UserRepo;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.Utils;

import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PaymentService {

    @Autowired
    private RazorpayOrderRepo razorpayOrderRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private EmailService emailService;

    @Value("${razorpay.api.key_id}")
    private String api_key;

    @Value("${razorpay.api.key_secret}")
    private String api_secret;

    public Order createOrder(OrderRequestDTO orderRequestDTO, Principal principal) throws RazorpayException {
        int amount = 50; // for now we only have one plan thats why hardcoded
                         // in future will add dynamic amount fetching from db
        RazorpayClient razorpayClient = new RazorpayClient(api_key, api_secret);
        JSONObject orderRequest = new JSONObject();
        orderRequest.put("amount", amount * 100); // amount is in paise for that *100
        orderRequest.put("currency", "INR");
        orderRequest.put("receipt", "121212");
        Order order = razorpayClient.orders.create(orderRequest);
        RazorpayOrder razorpayOrder = new RazorpayOrder();
        razorpayOrder.setRazorpayOrderId(UUID.randomUUID().toString()); // primary key of order table
        razorpayOrder.setAmount(order.get("amount") + "");
        razorpayOrder.setOrderId(order.get("id"));
        razorpayOrder.setReceipt(order.get("receipt"));
        razorpayOrder.setStatus(order.get("status"));
        razorpayOrder.setCurrency(order.get("currency"));
        // principal.getName()
        User user = userRepo.findByEmail(principal.getName())
                .orElseThrow(() -> new UsernameNotFoundException("user not fount with username"));
        razorpayOrder.setUserid(user.getId());
        razorpayOrder.setUsername(user.getEmail());
        razorpayOrderRepo.save(razorpayOrder);

        return order;
    }

    public boolean verifyRazorpayPayment(PaymentDetailsDTO paymentDetailsDTO, Principal principal) throws RazorpayException {
        JSONObject paymentDetails = new JSONObject();
        paymentDetails.put("razorpay_signature", paymentDetailsDTO.getRazorpaySignature());
        paymentDetails.put("razorpay_order_id", paymentDetailsDTO.getRazorpayOrderId());
        paymentDetails.put("razorpay_payment_id", paymentDetailsDTO.getRazorpayPaymentId());

        RazorpayOrder razorpayOrder = razorpayOrderRepo.findByOrderId(paymentDetailsDTO.getRazorpayOrderId()).orElse(null);
         User user = userRepo.findByEmail(principal.getName())
                .orElseThrow(() -> new UsernameNotFoundException("user not fount with username"));

        if (Utils.verifyPaymentSignature(paymentDetails, api_secret)) {
            razorpayOrder.setStatus("completed");
            razorpayOrder.setPaymentId(paymentDetailsDTO.getRazorpayOrderId());
            razorpayOrderRepo.save(razorpayOrder);
           
            user.setCurrentPlan("Basic_Plan");    //for now hard coded later will load the dynamically
            user.setApiCalls(user.getApiCalls()+200);

            // successfull payment email..........
            String html = MailHtmlHelper.planpurchased
                                        .replace("${username}", user.getName())
                                        .replace("${plan_purchased}", user.getCurrentPlan())
                                        .replace("${api_call_left}", String.valueOf(user.getApiCalls()))
                                        .replace("${amount_paid}", "50")
                                        .replace("${transaction_id}", paymentDetailsDTO.getRazorpayPaymentId());
            try {
                emailService.sendHtmlMail(user.getEmail(),"Transaction Confirmed Your "+ user.getCurrentPlan() +" Is Now Active", html);
            } catch (Exception e) {
                log.error("unable to send payment success mail", e);
            }
                                        
            userRepo.save(user);
            return true;
        } else {
            razorpayOrder.setStatus("failed");

            //payment failed email.........
            String html = MailHtmlHelper.paymentFailed
                                        .replace("${username}", user.getName())
                                        .replace("${plan_purchased}", "Basic Plan")
                                        .replace("${transaction_id}", paymentDetailsDTO.getRazorpayOrderId());
            try {
                emailService.sendHtmlMail(user.getEmail(),"Your Uttar-AI Payment Was Unsuccessful", html);
            } catch (MessagingException e) {
                log.error("unable to send payment failed mail", e);
            }
            razorpayOrderRepo.save(razorpayOrder);
            return false;
        }
    }


    // current plan   (plan name, api calls left)
    public CurrentPlan fetchCurrentPlan(Principal principal){
        User user = userRepo.findByEmail(principal.getName()).orElseThrow(()-> new UsernameNotFoundException("user not found "+ principal.getName()));
        CurrentPlan currentPlan = new CurrentPlan();
        currentPlan.setApiCallsLeft(String.valueOf(user.getApiCalls()));
        currentPlan.setCurrentPlanName(user.getCurrentPlan());
        return currentPlan;
    }
}
