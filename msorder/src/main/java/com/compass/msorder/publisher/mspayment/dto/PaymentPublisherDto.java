package com.compass.msorder.publisher.mspayment.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value = "Envio dos dados para o mspayment - pedido")
public class PaymentPublisherDto {

    @ApiModelProperty(value = "Id do pedido")
    private String orderId;

    @ApiModelProperty(value = "Pagamento")
    private PaymentPaymentPublisherDto payment;

}
