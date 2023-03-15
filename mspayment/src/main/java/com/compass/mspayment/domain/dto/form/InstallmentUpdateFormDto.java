package com.compass.mspayment.domain.dto.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value = "Envio dos dados de atualização da parcela")
public class InstallmentUpdateFormDto {

    @ApiModelProperty(value = "Quantidade")
    @NotNull
    private Long amount;

    @ApiModelProperty(value = "Marca")
    private String brand;

    @ApiModelProperty(value = "Id do pagamento")
    @NotNull
    private Long paymentId;
}
