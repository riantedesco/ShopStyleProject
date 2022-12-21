package com.compass.mscustomer.domain.dto.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value = "Envio dos dados de alteração de senha do cliente")
public class CustomerPasswordUpdateFormDto {

    @ApiModelProperty(value = "Nova senha")
    @NotNull
    @Size(min = 6, message = "Password must contain at least 6 characters")
    private String newPassword;

    @ApiModelProperty(value = "Confirmação da nova senha")
    @NotNull
    @Size(min = 6, message = "Password must contain at least 6 characters")
    private String confirmNewPassword;

}
