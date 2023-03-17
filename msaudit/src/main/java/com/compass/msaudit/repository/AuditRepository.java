package com.compass.msaudit.repository;

import com.compass.msaudit.domain.AuditDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface AuditRepository extends MongoRepository<AuditDocument, Long> {

    @Query("{ 'order.id' : ?0 }")
    List<AuditDocument> findByOrderId(String orderId);

}
