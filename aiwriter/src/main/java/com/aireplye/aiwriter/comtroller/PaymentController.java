package com.aireplye.aiwriter.comtroller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aireplye.aiwriter.dto.OrderRequestDTO;
import com.aireplye.aiwriter.dto.PaymentDetailsDTO;
import com.aireplye.aiwriter.service.PaymentService;
import com.razorpay.Order;
import com.razorpay.RazorpayException;


@RestController
@RequestMapping("/api/secure/payment")
@CrossOrigin(origins = "*")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;
    
    
    @PostMapping("/createorder")
    public ResponseEntity<?> createOrder(@RequestBody OrderRequestDTO orderRequestDTO, Principal principal) throws RazorpayException{
        Order order = paymentService.createOrder(orderRequestDTO, principal);
        System.out.println(order);
        return new ResponseEntity<>(order.toString(), HttpStatus.CREATED);
    }


    @PostMapping("/verifypayment")
    public ResponseEntity<?> verifyPayment(@RequestBody PaymentDetailsDTO paymentDetailsDTO) throws RazorpayException{
        System.out.println("verify payment called");
        // System.out.println(paymentDetailsDTO);
        if(paymentService.verifyRazorpayPayment(paymentDetailsDTO)){
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        
    }
}
