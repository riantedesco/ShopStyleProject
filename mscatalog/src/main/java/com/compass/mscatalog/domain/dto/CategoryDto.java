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
@ApiModel(value = "Retorno dos dados da categoria")
public class CategoryDto {

    @ApiModelProperty(value = "Id")
    private Long id;

    @ApiModelProperty(value = "Nome")
    private String name;

    @ApiModelProperty(value = "Status (ativo ou inativo)")
    private Boolean active;

    @ApiModelProperty(value = "Categorias filhas")
    private List<CategoryDto> children;
}
