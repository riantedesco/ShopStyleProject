package com.compass.msaudit.domain.dojo;

import com.compass.msaudit.utils.constants.StatusOrderOption;
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
public class Order {

    private String id;

    private Customer customer;

    private Payment payment;

    private List<Cart> cart;

    private LocalDateTime date;

    private StatusOrderOption status;

    private Double total;

}
