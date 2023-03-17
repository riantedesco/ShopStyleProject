package com.compass.mscatalog.listener.msorder.dto;

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
@ApiModel(value = "Chegada dos dados do msorder - sku")
public class SkuOrderListenerDto {

    @ApiModelProperty(value = "Id da sku")
    private Long id;

    @ApiModelProperty(value = "Quantidade da sku")
    private Long quantity;

}
