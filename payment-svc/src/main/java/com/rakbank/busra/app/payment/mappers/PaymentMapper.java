package com.rakbank.busra.app.payment.mappers;


import com.rakbank.busra.app.payment.dtos.PaymentDTO;
import com.rakbank.busra.app.payment.models.Payment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentMapper {
    Payment toPaymentEntity(PaymentDTO requestDTO);
    PaymentDTO toPaymentDTO(Payment payment);
}
