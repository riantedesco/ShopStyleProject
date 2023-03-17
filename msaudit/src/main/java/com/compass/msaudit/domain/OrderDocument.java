package com.compass.msaudit.domain;

import com.compass.msaudit.domain.dojo.Cart;
import com.compass.msaudit.domain.dojo.Customer;
import com.compass.msaudit.domain.dojo.Payment;
import com.compass.msaudit.utils.constants.StatusOrderOption;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.List;

@Document("Order")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDocument {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "customer")
    @DBRef
    private Customer customer;

    @Column(name = "payment")
    @DBRef
    private Payment payment;

    @Column(name = "cart")
    @DBRef
    private List<Cart> cart;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "status")
    private StatusOrderOption status;

    @Column(name = "total")
    private Double total;

}
