package com.compass.msaudit.domain.dto;

import com.compass.msaudit.domain.dojo.Order;
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
@ApiModel(value = "Retorno dos dados da auditoria")
public class AuditDto {

    @ApiModelProperty(value = "Id")
    private String id;

    @ApiModelProperty(value = "Pedido")
    private Order order;

}
