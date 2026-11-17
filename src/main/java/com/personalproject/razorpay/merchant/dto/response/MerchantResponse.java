package com.personalproject.razorpay.merchant.dto.response;

import com.personalproject.razorpay.common.enums.BusinessType;
import com.personalproject.razorpay.common.enums.MerchantStatus;
import lombok.Data;

import java.util.UUID;

public record MerchantResponse(
        UUID id,
        String name,
        String email,
        String businessName,
        BusinessType businessType,
        MerchantStatus merchantStatus
) {
}
