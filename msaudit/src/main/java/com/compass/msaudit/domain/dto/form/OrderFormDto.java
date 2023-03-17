package com.compass.msaudit.domain.dto.form;

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
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

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
    private LocalDateTime date;

    @ApiModelProperty(value = "Status")
    @NotNull
    private StatusOrderOption status;

    @ApiModelProperty(value = "Total")
    @NotNull
    private Double total;

}
