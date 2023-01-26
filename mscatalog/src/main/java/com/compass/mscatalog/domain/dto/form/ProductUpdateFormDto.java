package com.compass.mscatalog.domain.dto.form;

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
@ApiModel(value = "Envio dos dados de atualização do produto")
public class ProductUpdateFormDto {

    @ApiModelProperty(value = "Nome")
    @NotNull
    private String name;

    @ApiModelProperty(value = "Descrição")
    @NotNull
    private String description;

    @ApiModelProperty(value = "Marca")
    @NotNull
    private String brand;

    @ApiModelProperty(value = "Material")
    private String material;

    @ApiModelProperty(value = "Status (ativo ou inativo)")
    @NotNull
    private Boolean active;

}
