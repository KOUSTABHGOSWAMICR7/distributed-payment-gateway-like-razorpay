package com.personalproject.razorpay.merchant.dto.request;

import com.personalproject.razorpay.common.enums.Environment;

public record CreateApiKeyRequest(
        Environment environment
) {
}
