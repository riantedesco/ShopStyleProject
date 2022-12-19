package com.compass.mscustomer.domain.dto.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value = "Envio dos dados do cliente")
public class CustomerFormDto {

    @ApiModelProperty(value = "Cpf do cliente")
    @NotNull
    @Size(min = 14, max = 14, message = "Cpf must contain 11 characters")
    private String cpf;

    @ApiModelProperty(value = "Nome do cliente")
    @NotNull
    @Size(min = 3, message = "First name must contain at least 3 characters")
    private String firstName;

    @ApiModelProperty(value = "Sobrenome do cliente")
    @NotNull
    @Size(min = 3, message = "Last name must contain at least 3 characters")
    private String lastName;

    @ApiModelProperty(value = "Sexo do cliente")
    @NotNull
    private String sex;

    @ApiModelProperty(value = "Data de nascimento do cliente")
    @NotNull
    private LocalDate birthdate;

    @ApiModelProperty(value = "Email do cliente")
    @NotNull
    private String email;

    @ApiModelProperty(value = "Email do cliente")
    @NotNull
    private String password;

    @Column(name = "active")
    private Double active;




    @ApiModelProperty(value = "Nome do cliente")
    @NotNull
    @Size(min = 3, message = "Name must contain at least 3 characters")
    private String name;

    @ApiModelProperty(value = "Sexo do cliente")
    @NotNull
    private String sex;

    @ApiModelProperty(value = "Data de nascimento do cliente")
    @NotNull
    private LocalDate birthdate;

    @ApiModelProperty(value = "Id da cidade do cliente")
    @NotNull
    private Long idCity;

}
