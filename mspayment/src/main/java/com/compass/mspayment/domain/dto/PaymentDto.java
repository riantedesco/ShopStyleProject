package com.compass.mspayment.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value = "Retorno dos dados do pagamento")
public class PaymentDto {

    @ApiModelProperty(value = "Id")
    private Long id;

    @ApiModelProperty(value = "Tipo")
    private String type;

    @ApiModelProperty(value = "Parcelamento (sim ou não)")
    private Boolean existsInstallments;

    @ApiModelProperty(value = "Status (ativo ou inativo)")
    private Boolean active;

    @ApiModelProperty(value = "Parcelas")
    private List<InstallmentDto> installments;
}
