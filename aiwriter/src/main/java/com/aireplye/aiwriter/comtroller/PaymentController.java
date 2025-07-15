package com.aireplye.aiwriter.comtroller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aireplye.aiwriter.dto.OrderRequestDTO;
import com.aireplye.aiwriter.service.PaymentService;
import com.razorpay.Order;
import com.razorpay.RazorpayException;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/secure/payment")
@CrossOrigin(origins = "*")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;
    
    
    @PostMapping("/createorder")
    public ResponseEntity<?> createOrder(@RequestBody OrderRequestDTO orderRequestDTO) throws RazorpayException{
        Order order = paymentService.createOrder(orderRequestDTO);
        System.out.println(order);
        
        return new ResponseEntity<>(order.toString(), HttpStatus.CREATED);
    }
}
