package com.personalproject.razorpay.operations.repository;

import com.personalproject.razorpay.operations.entity.SettlementPayment;
import com.personalproject.razorpay.operations.entity.SettlementPaymentId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SettlementPaymentRepository extends JpaRepository<SettlementPayment, SettlementPaymentId> {
}
