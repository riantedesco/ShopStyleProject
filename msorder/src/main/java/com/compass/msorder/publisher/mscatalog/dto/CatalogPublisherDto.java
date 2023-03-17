package com.compass.msorder.publisher.mscatalog.dto;

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
@ApiModel(value = "Envio dos dados para o mscatalog - pedido")
public class CatalogPublisherDto {

    @ApiModelProperty(value = "Id do pedido")
    private String orderId;

    @ApiModelProperty(value = "Skus")
    private List<SkuCatalogPublisherDto> skus;

}