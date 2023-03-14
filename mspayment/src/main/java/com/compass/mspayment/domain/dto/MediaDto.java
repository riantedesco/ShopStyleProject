package com.compass.mspayment.domain.dto;

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
@ApiModel(value = "Retorno dos dados da imagem")
public class MediaDto {

    @ApiModelProperty(value = "Id")
    private Long id;

    @ApiModelProperty(value = "Url")
    private String imageUrl;

}
