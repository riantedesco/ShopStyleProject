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
@ApiModel(value = "Envio dos dados do pagamento")
public class PaymentFormDto {

    @ApiModelProperty(value = "Tipo")
    @NotNull
    private String type;

    @ApiModelProperty(value = "Parcelas (sim ou não)")
    @NotNull
    private Boolean existsInstallments;

    @ApiModelProperty(value = "Status (ativo ou inativo)")
    @NotNull
    private Boolean active;
}
