package com.personalproject.razorpay.payment.mapper;

import com.personalproject.razorpay.payment.dto.response.OrderResponse;
import com.personalproject.razorpay.payment.entity.OrderRecord;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderMapper {

    OrderResponse toResponse(OrderRecord orderRecord);
}
