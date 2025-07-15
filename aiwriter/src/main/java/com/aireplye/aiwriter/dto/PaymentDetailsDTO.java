package com.aireplye.aiwriter.dto;

import lombok.Data;

@Data
public class PaymentDetailsDTO {
    private String razorpayPaymentId;
    private String razorpayOrderId;
    private String razorpaySignature;
}
