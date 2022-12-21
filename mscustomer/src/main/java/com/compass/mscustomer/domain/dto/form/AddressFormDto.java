package com.compass.mscustomer.domain.dto.form;

import com.compass.mscustomer.util.constants.StateAddressOption;
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
@ApiModel(value = "Envio dos dados do endereço")
public class AddressFormDto {

    @ApiModelProperty(value = "Estado")
    @NotNull
    private StateAddressOption state;

    @ApiModelProperty(value = "Cidade")
    @NotNull
    private String city;

    @ApiModelProperty(value = "Distrito")
    @NotNull
    private String district;

    @ApiModelProperty(value = "Rua")
    @NotNull
    private String street;

    @ApiModelProperty(value = "Número")
    @NotNull
    private String number;

    @ApiModelProperty(value = "Cep")
    @NotNull
    private String cep;

    @ApiModelProperty(value = "Complemento")
    private String complement;

    @ApiModelProperty(value = "Id do cliente")
    @NotNull
    private Long customerId;

}
