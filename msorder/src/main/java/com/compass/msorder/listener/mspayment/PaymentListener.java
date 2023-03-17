package com.compass.msorder.listener.mspayment;

import com.compass.msorder.domain.OrderDocument;
import com.compass.msorder.listener.mspayment.dto.PaymentListenerDto;
import com.compass.msorder.publisher.msaudit.AuditPublisher;
import com.compass.msorder.publisher.mscatalog.CatalogPublisher;
import com.compass.msorder.repository.OrderRepository;
import com.compass.msorder.utils.constants.RabbitMQConstants;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.compass.msorder.utils.constants.StatusOrderOption.PAYMENT_SUCCESSFUL;

@Component
@Slf4j
public class PaymentListener {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CatalogPublisher catalogPublisher;

    @Autowired
    private AuditPublisher auditPublisher;

    @RabbitListener(queues = RabbitMQConstants.PAYMENT_TO_ORDER_QUEUE_NAME)
    public void listenPayment(PaymentListenerDto paymentListenerDto) {
        log.info("PaymentListener.listen - {}", paymentListenerDto);
        Optional<OrderDocument> order = this.orderRepository.findById(paymentListenerDto.getOrderId());
        order.get().setStatus(paymentListenerDto.getStatus());
        this.orderRepository.save(order.get());

        if (order.get().getStatus().equals(PAYMENT_SUCCESSFUL)) {
            catalogPublisher.publishCatalog(order.get());
        }

        auditPublisher.publishAudit(order.get());
    }
}
