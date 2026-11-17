package com.personalproject.razorpay.merchant.service;

import com.personalproject.razorpay.merchant.dto.request.LoginRequest;
import com.personalproject.razorpay.merchant.dto.request.MerchantSignupRequest;
import com.personalproject.razorpay.merchant.dto.response.LoginResponse;
import com.personalproject.razorpay.merchant.dto.response.MerchantResponse;
import jakarta.validation.Valid;
import org.jspecify.annotations.Nullable;

public interface AuthService {
    MerchantResponse signup(MerchantSignupRequest request);

    LoginResponse login(LoginRequest request);
}
