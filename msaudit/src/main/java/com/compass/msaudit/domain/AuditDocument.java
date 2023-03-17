package com.compass.msaudit.domain;

import com.compass.msaudit.domain.dojo.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("AuditDocument")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuditDocument {

    @Id
    private String id;

    private Order order;

    public AuditDocument(Order order) {
        this.order = order;
    }

}
