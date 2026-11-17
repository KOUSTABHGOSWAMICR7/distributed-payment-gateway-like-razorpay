package com.personalproject.razorpay.merchant.mapper;

import com.personalproject.razorpay.merchant.dto.request.MerchantSignupRequest;
import com.personalproject.razorpay.merchant.dto.response.MerchantResponse;
import com.personalproject.razorpay.merchant.entity.Merchant;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MerchantMapper {

    Merchant toEntityFromSignUpRequest(MerchantSignupRequest request);

    MerchantResponse toResponse(Merchant merchant);
}
