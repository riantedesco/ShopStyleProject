package com.compass.msorder.publisher.msaudit;

import com.compass.msorder.domain.OrderDocument;
import com.compass.msorder.domain.dto.OrderDto;
import com.compass.msorder.domain.dto.form.OrderFormDto;
import com.compass.msorder.publisher.msaudit.dto.AuditPublisherDto;
import com.compass.msorder.publisher.mspayment.dto.PaymentPaymentPublisherDto;
import com.compass.msorder.utils.constants.RabbitMQConstants;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AuditPublisher {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ModelMapper mapper;

    public void publishAudit(OrderDocument order) {
        AuditPublisherDto auditPublisherDto = mapper.map(order, AuditPublisherDto.class);
        rabbitTemplate.convertAndSend(RabbitMQConstants.EXCHANGE_NAME, RabbitMQConstants.ORDER_TO_AUDIT_ROUTINGKEY_NAME, auditPublisherDto);
        log.info("AuditPublisher.publish - {}", auditPublisherDto);
    }
}
