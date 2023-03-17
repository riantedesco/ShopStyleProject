package com.compass.mspayment.listener.msorder;

import com.compass.mspayment.domain.InstallmentEntity;
import com.compass.mspayment.domain.PaymentEntity;
import com.compass.mspayment.exception.InvalidAttributeException;
import com.compass.mspayment.listener.msorder.dto.OrderListenerDto;
import com.compass.mspayment.publisher.msorder.OrderPublisher;
import com.compass.mspayment.repository.InstallmentRepository;
import com.compass.mspayment.repository.PaymentRepository;
import com.compass.mspayment.util.constants.RabbitMQConstants;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static com.compass.mspayment.util.constants.StatusOrderPublisherOption.*;

@Component
@Slf4j
public class OrderListener {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private OrderPublisher orderPublisher;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private InstallmentRepository installmentRepository;

    @RabbitListener(queues = RabbitMQConstants.ORDER_TO_PAYMENT_QUEUE_NAME)
    public void listenOrder(OrderListenerDto orderListenerDto) {
        log.info("OrderListener.listen - {}", orderListenerDto);

        Optional<PaymentEntity> payment = this.paymentRepository.findById(orderListenerDto.getPayment().getId());
        if(!payment.isPresent()) {
            throw new InvalidAttributeException("Payment " + orderListenerDto.getPayment().getId() + " not found");
        }

        List<InstallmentEntity> installments = this.installmentRepository.findByPaymentId(orderListenerDto.getPayment().getId());
        int amountNotExceeded = 0;
        for (InstallmentEntity installment: installments) {
            if (orderListenerDto.getPayment().getInstallments() <= installment.getAmount()) {
                amountNotExceeded++;
            }
        }

        if (!payment.isPresent()) {
            orderPublisher.publishOrder(orderListenerDto.getOrderId(), PAYMENT_NOT_FOUND);
        } else if (payment.get().getActive().equals(false)) {
            orderPublisher.publishOrder(orderListenerDto.getOrderId(), PAYMENT_INACTIVE);
        } else if (payment.get().getExistsInstallments().equals(false)) {
            orderPublisher.publishOrder(orderListenerDto.getOrderId(), PAYMENT_NOT_INSTALLMENT);
        } else if (amountNotExceeded == 0) {
            orderPublisher.publishOrder(orderListenerDto.getOrderId(), PAYMENT_AMOUNT_NOT_AVAILABLE);
        } else {
            orderPublisher.publishOrder(orderListenerDto.getOrderId(), PAYMENT_SUCCESSFUL);
        }
    }
}
