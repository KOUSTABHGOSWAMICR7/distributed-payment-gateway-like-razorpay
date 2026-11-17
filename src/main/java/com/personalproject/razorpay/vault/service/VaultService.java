package com.personalproject.razorpay.vault.service;

import com.personalproject.razorpay.common.entity.Money;
import com.personalproject.razorpay.payment.processor.dto.PaymentProcessorResponse;
import com.personalproject.razorpay.vault.dto.request.TokenizeRequest;
import com.personalproject.razorpay.vault.dto.response.TokenizeResponse;
import org.jspecify.annotations.Nullable;

import java.util.Map;
import java.util.UUID;

public interface VaultService {

    TokenizeResponse tokenize(TokenizeRequest request, UUID merchantId);

    PaymentProcessorResponse charge(UUID paymentId, String token, Money amount, Map<String, Object> methodDetails);
}
