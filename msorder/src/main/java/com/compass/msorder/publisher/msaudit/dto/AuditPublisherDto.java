package com.compass.msorder.publisher.msaudit.dto;

import com.compass.msorder.domain.dojo.Cart;
import com.compass.msorder.domain.dojo.Customer;
import com.compass.msorder.domain.dojo.Payment;
import com.compass.msorder.utils.constants.StatusOrderOption;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value = "Envio dos dados para o msaudit - pedido")
public class AuditPublisherDto {

    @ApiModelProperty(value = "Id")
    private String id;

    @ApiModelProperty(value = "Cliente")
    private Customer customer;

    @ApiModelProperty(value = "Pagamento")
    private Payment payment;

    @ApiModelProperty(value = "Carrinho")
    private List<Cart> cart;

    @ApiModelProperty(value = "Data")
    private LocalDateTime date;

    @ApiModelProperty(value = "Status")
    private StatusOrderOption status;

    @ApiModelProperty(value = "Total")
    private Double total;

}
