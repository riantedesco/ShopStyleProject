package com.compass.mscatalog.domain.dto;

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
@ApiModel(value = "Retorno dos dados do produto com suas skus")
public class ProductWithSkusDto {

    @ApiModelProperty(value = "Id")
    private Long id;

    @ApiModelProperty(value = "Nome")
    private String name;

    @ApiModelProperty(value = "Descrição")
    private String description;

    @ApiModelProperty(value = "Marca")
    private String brand;

    @ApiModelProperty(value = "Material")
    private String material;

    @ApiModelProperty(value = "Status (ativo ou inativo)")
    private Boolean active;

    @ApiModelProperty(value = "Skus")
    private List<SkuDto> skus;
}
