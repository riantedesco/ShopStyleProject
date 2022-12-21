package com.compass.mscustomer.security;

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
@ApiModel(value = "Retorno do token")
public class TokenDto {

	@ApiModelProperty(value = "Token")
	private String token;

	@ApiModelProperty(value = "Tipo")
	private String type;

}
