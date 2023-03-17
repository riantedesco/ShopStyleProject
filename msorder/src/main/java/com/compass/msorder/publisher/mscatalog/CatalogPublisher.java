package com.compass.msorder.publisher.mscatalog;

import com.compass.msorder.domain.OrderDocument;
import com.compass.msorder.domain.dojo.Cart;
import com.compass.msorder.publisher.mscatalog.dto.CatalogPublisherDto;
import com.compass.msorder.publisher.mscatalog.dto.SkuCatalogPublisherDto;
import com.compass.msorder.utils.constants.RabbitMQConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class CatalogPublisher {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void publishCatalog(OrderDocument order) {
        CatalogPublisherDto catalogPublisherDto = new CatalogPublisherDto();
        catalogPublisherDto.setOrderId(order.getId());

        List<SkuCatalogPublisherDto> skus = new ArrayList<>();
        for (Cart sku: order.getCart()) {
            skus.add(new SkuCatalogPublisherDto(sku.getSkuId(), sku.getQuantity()));
        }

        catalogPublisherDto.setSkus(skus);

        rabbitTemplate.convertAndSend(RabbitMQConstants.EXCHANGE_NAME, RabbitMQConstants.ORDER_TO_CATALOG_ROUTINGKEY_NAME, catalogPublisherDto);
        log.info("CatalogPublisher.publish - {}", catalogPublisherDto);
    }
}
