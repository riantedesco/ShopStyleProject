package com.compass.msorder.publisher.mscatalog.dto;

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
@ApiModel(value = "Envio dos dados para o mscatalog - sku")
public class SkuCatalogPublisherDto {

    @ApiModelProperty(value = "Id da sku")
    private Long id;

    @ApiModelProperty(value = "Quantidade da sku")
    private Long quantity;

}
