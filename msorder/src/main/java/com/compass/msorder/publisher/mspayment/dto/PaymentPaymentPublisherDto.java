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
@ApiModel(value = "Envio dos dados para o mspayment - pagamento")
public class PaymentPaymentPublisherDto {

    @ApiModelProperty(value = "Id do pagamento")
    private Long id;

    @ApiModelProperty(value = "Parcelas do pagamento")
    private Long installments;

}
