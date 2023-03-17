package com.compass.msorder.domain;

import com.compass.msorder.domain.dojo.Cart;
import com.compass.msorder.domain.dojo.Customer;
import com.compass.msorder.domain.dojo.Payment;
import com.compass.msorder.utils.constants.StatusOrderOption;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Document("OrderDocument")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDocument {

    @Id
    private String id;

    private Customer customer;

    private Payment payment;

    private List<Cart> cart;

    private LocalDateTime date;

    private StatusOrderOption status;

    private Double total;

}
