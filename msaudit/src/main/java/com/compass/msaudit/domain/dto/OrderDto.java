package com.compass.msaudit.domain.dto;

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

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value = "Retorno dos dados do pedido")
public class OrderDto {

    @ApiModelProperty(value = "Id")
    private Long id;

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
