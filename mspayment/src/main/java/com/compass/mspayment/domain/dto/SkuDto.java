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
@ApiModel(value = "Retorno dos dados da sku")
public class SkuDto {

    @ApiModelProperty(value = "Id")
    private Long id;

    @ApiModelProperty(value = "Preço")
    private Double price;

    @ApiModelProperty(value = "Quantidade")
    private Long quantity;

    @ApiModelProperty(value = "Cor")
    private String color;

    @ApiModelProperty(value = "Tamanho")
    private String size;

    @ApiModelProperty(value = "Altura")
    private Double height;

    @ApiModelProperty(value = "Largura")
    private Double width;

    @ApiModelProperty(value = "Imagens")
    private List<MediaDto> medias;

}
