package com.compass.mspayment.listener.msorder.dto;

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
@ApiModel(value = "Chegada dos dados do msorder - pagamento")
public class PaymentOrderListenerDto {

    @ApiModelProperty(value = "Id do pagamento")
    private Long id;

    @ApiModelProperty(value = "Parcelas do pagamento")
    private Long installments;

}
