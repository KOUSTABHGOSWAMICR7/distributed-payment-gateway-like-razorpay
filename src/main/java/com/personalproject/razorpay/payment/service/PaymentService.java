package com.personalproject.razorpay.payment.service;

import com.personalproject.razorpay.payment.dto.request.PaymentInitRequest;
import com.personalproject.razorpay.payment.dto.response.PaymentResponse;
import org.jspecify.annotations.Nullable;

import java.util.UUID;

public interface PaymentService {

    PaymentResponse initiate(UUID merchantId, PaymentInitRequest request);

    PaymentResponse capture(UUID merchantId, UUID paymentId);

    void resolveAuthorization(UUID paymentId, boolean approve, String bankRef, String errorCode, String errorDescription);
}
