package com.personalproject.razorpay.payment.service.impl;

import com.personalproject.razorpay.common.enums.PaymentStatus;
import com.personalproject.razorpay.payment.api.PaymentLookupService;
import com.personalproject.razorpay.payment.entity.Payment;
import com.personalproject.razorpay.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentLookupServiceImpl implements PaymentLookupService {

    private final PaymentRepository paymentRepository;

    @Override
    public List<Payment> findUnsettledCapturedPayments(UUID merchantId) {
        return paymentRepository.findByMerchantIdAndStatusForUpdate(merchantId, PaymentStatus.CAPTURED);
    }
}













