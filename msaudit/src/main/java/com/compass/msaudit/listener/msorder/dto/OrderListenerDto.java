package com.compass.msaudit.listener.msorder.dto;

import com.compass.msaudit.domain.dojo.Cart;
import com.compass.msaudit.domain.dojo.Customer;
import com.compass.msaudit.domain.dojo.Payment;
import com.compass.msaudit.utils.constants.StatusOrderOption;
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
@ApiModel(value = "Chegada dos dados do msorder - pedido")
public class OrderListenerDto {

    @ApiModelProperty(value = "Id do pedido")
    private String orderId;

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
