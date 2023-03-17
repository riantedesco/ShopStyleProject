package com.compass.msorder.domain.dto.form;

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

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

import static com.compass.msorder.utils.constants.StatusOrderOption.PROCESSING_PAYMENT;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value = "Envio dos dados do pedido")
public class OrderFormDto {

    @ApiModelProperty(value = "Cliente")
    @NotNull
    private Customer customer;

    @ApiModelProperty(value = "Pagamento")
    @NotNull
    private Payment payment;

    @ApiModelProperty(value = "Carrinho")
    @NotNull
    private List<Cart> cart;

    @ApiModelProperty(value = "Data")
    @NotNull
    private LocalDateTime date = LocalDateTime.now();

    @ApiModelProperty(value = "Status")
    @NotNull
    private StatusOrderOption status = PROCESSING_PAYMENT;

    @ApiModelProperty(value = "Total")
    @NotNull
    private Double total = 0.00;

}
