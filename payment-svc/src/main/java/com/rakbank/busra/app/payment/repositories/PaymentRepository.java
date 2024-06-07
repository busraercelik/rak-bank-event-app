package com.rakbank.busra.app.payment.repositories;


import com.rakbank.busra.app.payment.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
