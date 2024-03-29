package com.compass.mscatalog.domain.dto.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value = "Envio dos dados de atualização da sku")
public class SkuUpdateFormDto {

    @ApiModelProperty(value = "Preço")
    @NotNull
    private Double price;

    @ApiModelProperty(value = "Quantidade")
    @NotNull
    private Long quantity;

    @ApiModelProperty(value = "Cor")
    @NotNull
    private String color;

    @ApiModelProperty(value = "Tamanho")
    @NotNull
    private String size;

    @ApiModelProperty(value = "Altura")
    @NotNull
    private Double height;

    @ApiModelProperty(value = "Largura")
    @NotNull
    private Double width;

    @ApiModelProperty(value = "Imagens")
    @NotNull
    private List<String> images;

    @ApiModelProperty(value = "Id do produto")
    @NotNull
    private Long productId;

}
