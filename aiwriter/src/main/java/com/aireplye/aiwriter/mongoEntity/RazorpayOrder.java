package com.aireplye.aiwriter.mongoEntity;

import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.mapping.Document;

import org.springframework.data.annotation.Id;
import lombok.Data;

@Data
@Profile({"mongodb", "prod"})
@Document(collection = "razorPayment")
public class RazorpayOrder {
    @Id
    private String razorpayOrderId;
    private String amount;
    private String orderId;
    private String paymentId;
    private String status;
    private String receipt;
    private String currency;


    private String userid;
    private String username;
}
