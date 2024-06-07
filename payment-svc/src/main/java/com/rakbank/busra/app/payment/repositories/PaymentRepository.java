package com.rakbank.busra.app.payment.repositories;


import com.rakbank.busra.app.payment.common.exceptions.ApplicationException;
import com.rakbank.busra.app.payment.errors.ErrorCode;
import com.rakbank.busra.app.payment.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    default Payment getByPaymentId(Long paymentId){
        return findById(paymentId).orElseThrow(()->
                new ApplicationException("Payment not found", ErrorCode.PAYMENT_NOT_FOUND));
    }
}
