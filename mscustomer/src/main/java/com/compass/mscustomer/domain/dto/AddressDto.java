package com.compass.mscustomer.domain.dto;

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
@ApiModel(value = "Retorno dos dados do endereço")
public class AddressDto {

    @ApiModelProperty(value = "Id")
    private Long id;

    @ApiModelProperty(value = "Estado")
    private StateAddressOption state;

    @ApiModelProperty(value = "Cidade")
    private String city;

    @ApiModelProperty(value = "Distrito")
    private String district;

    @ApiModelProperty(value = "Rua")
    private String street;

    @ApiModelProperty(value = "Número")
    private String number;

    @ApiModelProperty(value = "Cep")
    private String cep;

    @ApiModelProperty(value = "Complemento")
    private String complement;

    @ApiModelProperty(value = "Id do cliente")
    private Long customerId;

}
