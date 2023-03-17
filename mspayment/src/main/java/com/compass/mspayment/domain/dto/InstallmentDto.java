package com.compass.mspayment.domain.dto;

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
@ApiModel(value = "Retorno dos dados da parcela")
public class InstallmentDto {

    @ApiModelProperty(value = "Id ")
    private Long id;

    @ApiModelProperty(value = "Quantidade")
    private Long amount;

    @ApiModelProperty(value = "Marca")
    private String brand;

    @ApiModelProperty(value = "Id do pagamento")
    private Long paymentId;
}
