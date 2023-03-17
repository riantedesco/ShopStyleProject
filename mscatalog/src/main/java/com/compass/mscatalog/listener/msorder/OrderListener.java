package com.compass.mscatalog.listener.msorder;

import com.compass.mscatalog.domain.SkuEntity;
import com.compass.mscatalog.exception.InvalidAttributeException;
import com.compass.mscatalog.listener.msorder.dto.OrderListenerDto;
import com.compass.mscatalog.listener.msorder.dto.SkuOrderListenerDto;
import com.compass.mscatalog.repository.SkuRepository;
import com.compass.mscatalog.utils.constants.RabbitMQConstants;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
public class OrderListener {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private SkuRepository skuRepository;

    @RabbitListener(queues = RabbitMQConstants.ORDER_TO_CATALOG_QUEUE_NAME)
    public void listenOrder(OrderListenerDto orderListenerDto) {
        log.info("OrderListener.listen - {}", orderListenerDto);

        for (SkuOrderListenerDto skus: orderListenerDto.getSkus()) {
            Optional<SkuEntity> sku = this.skuRepository.findById(skus.getId());
            if(!sku.isPresent()) {
                throw new InvalidAttributeException("Sku " + skus.getId() + " not found");
            }
            sku.get().setQuantity(skus.getQuantity() - skus.getQuantity());
        }
    }
}
