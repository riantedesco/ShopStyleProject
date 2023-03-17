package com.compass.msaudit.listener.msorder;

import com.compass.msaudit.domain.AuditDocument;
import com.compass.msaudit.domain.dojo.Order;
import com.compass.msaudit.listener.msorder.dto.OrderListenerDto;
import com.compass.msaudit.repository.AuditRepository;
import com.compass.msaudit.utils.constants.RabbitMQConstants;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrderListener {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private AuditRepository auditRepository;

    @RabbitListener(queues = RabbitMQConstants.ORDER_TO_AUDIT_QUEUE_NAME)
    public void listenOrder(OrderListenerDto orderListenerDto) {
        log.info("OrderListener.listen - {}", orderListenerDto);
        Order order = mapper.map(orderListenerDto, Order.class);
        AuditDocument audit = new AuditDocument(order);
        this.auditRepository.save(audit);
    }
}
