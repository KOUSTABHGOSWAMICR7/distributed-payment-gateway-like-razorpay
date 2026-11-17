package com.personalproject.razorpay.payment.processor;

import com.personalproject.razorpay.payment.processor.dto.PaymentProcessorRequest;
import com.personalproject.razorpay.payment.processor.dto.PaymentProcessorResponse;

public interface PaymentProcessor {

    PaymentProcessorResponse charge(PaymentProcessorRequest request);

}
