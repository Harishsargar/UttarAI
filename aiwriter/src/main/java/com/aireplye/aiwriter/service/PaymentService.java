package com.aireplye.aiwriter.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.aireplye.aiwriter.dto.OrderRequestDTO;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@Service
public class PaymentService {
    @Value("${razorpay.api.key_id}")
    private String api_key;

    @Value("${razorpay.api.key_secret}")
    private String api_secret;
    
    public Order createOrder(OrderRequestDTO orderRequestDTO) throws RazorpayException{
        int amount = 50;     // for now we only have one plan thats why hardcoded 
                             //  in future will add dynamic amount fetching from db 
        RazorpayClient razorpayClient = new RazorpayClient(api_key, api_secret);
        JSONObject orderRequest = new JSONObject();
        orderRequest.put("amount", amount*100);  // amount is in paise for that *100
        orderRequest.put("currency","INR");
        orderRequest.put("receipt", "121212");
        Order order = razorpayClient.orders.create(orderRequest);
        return order;
    }
}


