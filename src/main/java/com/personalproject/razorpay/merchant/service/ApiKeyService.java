package com.personalproject.razorpay.merchant.service;

import com.personalproject.razorpay.merchant.dto.request.CreateApiKeyRequest;
import com.personalproject.razorpay.merchant.dto.response.ApiKeyCreateResponse;
import com.personalproject.razorpay.merchant.dto.response.ApiKeyResponse;
import org.jspecify.annotations.Nullable;

import java.util.List;
import java.util.UUID;

public interface ApiKeyService {

    ApiKeyCreateResponse create(UUID merchantId, CreateApiKeyRequest request);

    List<ApiKeyResponse> listByMerchant(UUID merchantId);

    void revoke(UUID merchantId, UUID keyId);

    @Nullable ApiKeyCreateResponse rotate(UUID merchantId, UUID keyId);
}
