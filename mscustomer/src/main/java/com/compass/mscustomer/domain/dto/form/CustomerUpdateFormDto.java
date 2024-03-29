package com.compass.mscustomer.domain.dto.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value = "Envio dos dados de atualização do cliente")
public class CustomerUpdateFormDto {

    @ApiModelProperty(value = "Cpf")
    @NotNull
    @Size(min = 14, max = 14, message = "Cpf must follow the pattern xxx.xxx.xxx-xx")
    @CPF(message = "Invalid cpf")
    private String cpf;

    @ApiModelProperty(value = "Nome")
    @NotNull
    @Size(min = 3, message = "First name must contain at least 3 characters")
    private String firstName;

    @ApiModelProperty(value = "Sobrenome")
    @NotNull
    @Size(min = 3, message = "Last name must contain at least 3 characters")
    private String lastName;

    @ApiModelProperty(value = "Sexo")
    @NotNull
    private String sex;

    @ApiModelProperty(value = "Data de nascimento")
    @NotNull
    private LocalDate birthdate;

    @ApiModelProperty(value = "Email")
    @NotNull
    @Email(message = "Invalid email")
    private String email;

    @ApiModelProperty(value = "Status (ativo ou inativo)")
    @NotNull
    private Boolean active;

}
