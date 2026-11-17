package com.personalproject.razorpay.operations.settlement;

import com.personalproject.razorpay.common.entity.Money;
import com.personalproject.razorpay.operations.settlement.dto.BankTransferResult;

import java.util.UUID;

public interface BankTransferProcessor {

    BankTransferResult initiate(UUID settlementId, UUID merchantId, Money amount,
                                String bankAccount, String ifsc);
}
