package com.compass.mscatalog.listener.msorder.dto;

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
@ApiModel(value = "Chegada dos dados do msorder - pedido")
public class OrderListenerDto {

    @ApiModelProperty(value = "Id do pedido")
    private Long orderId;

    @ApiModelProperty(value = "Skus")
    private List<SkusOrderListenerDto> skus;

}
