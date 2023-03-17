package com.compass.msorder.listener.mspayment.dto;

import com.compass.msorder.utils.constants.StatusOrderOption;
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
@ApiModel(value = "Chegada dos dados do mspayment - pedido")
public class PaymentListenerDto {

    @ApiModelProperty(value = "Id do pedido")
    private Long orderId;

    @ApiModelProperty(value = "Status do pagamento")
    private StatusOrderOption status;

}
