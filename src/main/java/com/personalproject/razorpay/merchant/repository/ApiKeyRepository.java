package com.personalproject.razorpay.merchant.repository;

import com.personalproject.razorpay.merchant.dto.response.ApiKeyResponse;
import com.personalproject.razorpay.merchant.entity.ApiKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ApiKeyRepository extends JpaRepository<ApiKey, UUID> {
    List<ApiKey> findByMerchant_Id(UUID merchantId);

    Optional<ApiKey> findByKeyId(String keyId);
}
