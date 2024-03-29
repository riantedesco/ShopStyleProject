package com.compass.mspayment.publisher.msorder;

import com.compass.mspayment.publisher.msorder.dto.OrderPublisherDto;
import com.compass.mspayment.util.constants.RabbitMQConstants;
import com.compass.mspayment.util.constants.StatusOrderPublisherOption;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrderPublisher {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void publishOrder(String id, StatusOrderPublisherOption status) {
        OrderPublisherDto orderPublisherDto = new OrderPublisherDto(id, status);
        rabbitTemplate.convertAndSend(RabbitMQConstants.EXCHANGE_NAME, RabbitMQConstants.PAYMENT_TO_ORDER_ROUTINGKEY_NAME, orderPublisherDto);
        log.info("OrderPublisher.publish - {}", orderPublisherDto);
    }
}
