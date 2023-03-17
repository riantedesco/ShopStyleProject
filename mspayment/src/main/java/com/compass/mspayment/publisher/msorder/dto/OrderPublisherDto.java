package com.compass.mspayment.publisher.msorder.dto;

import com.compass.mspayment.util.constants.StatusOrderPublisherOption;
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
@ApiModel(value = "Envio dos dados para o msorder")
public class OrderPublisherDto {

    @ApiModelProperty(value = "Id do pedido")
    private String orderId;

    @ApiModelProperty(value = "Status do pagamento")
    private StatusOrderPublisherOption status;

}
