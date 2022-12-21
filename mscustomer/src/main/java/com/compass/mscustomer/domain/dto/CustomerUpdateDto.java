package com.compass.mscustomer.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value = "Retorno dos dados de atualização do cliente")
public class CustomerUpdateDto {

    @ApiModelProperty(value = "Id")
    private Long id;

    @ApiModelProperty(value = "Cpf")
    private String cpf;

    @ApiModelProperty(value = "Nome")
    private String firstName;

    @ApiModelProperty(value = "Sobrenome")
    private String lastName;

    @ApiModelProperty(value = "Sexo")
    private String sex;

    @ApiModelProperty(value = "Data de nascimento")
    private LocalDate birthdate;

    @ApiModelProperty(value = "Email")
    private String email;

    @ApiModelProperty(value = "Senha ")
    private String password;

    @ApiModelProperty(value = "Status (ativo ou inativo)")
    private Boolean active;

}
