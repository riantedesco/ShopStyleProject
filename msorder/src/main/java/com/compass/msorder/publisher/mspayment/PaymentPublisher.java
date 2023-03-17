package com.compass.msorder.publisher.mspayment;

import com.compass.msorder.domain.OrderDocument;
import com.compass.msorder.publisher.msaudit.AuditPublisher;
import com.compass.msorder.publisher.mspayment.dto.PaymentPaymentPublisherDto;
import com.compass.msorder.publisher.mspayment.dto.PaymentPublisherDto;
import com.compass.msorder.utils.constants.RabbitMQConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PaymentPublisher {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private AuditPublisher auditPublisher;

    public void publishPayment(OrderDocument order) {
        PaymentPublisherDto paymentPublisherDto = new PaymentPublisherDto(order.getId(), new PaymentPaymentPublisherDto(order.getPayment().getId(), order.getPayment().getInstallments()));
        rabbitTemplate.convertAndSend(RabbitMQConstants.EXCHANGE_NAME, RabbitMQConstants.ORDER_TO_PAYMENT_ROUTINGKEY_NAME, paymentPublisherDto);
        log.info("PaymentPublisher.publish - {}", paymentPublisherDto);
        auditPublisher.publishAudit(order);
    }
}
