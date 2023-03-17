package com.compass.msaudit.repository;

import com.compass.msaudit.domain.OrderDocument;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderRepository extends MongoRepository<OrderDocument, Long> {

//    @Query("SELECT c FROM CategoryEntity c WHERE c.parent.id = :parentId")
//    List<OrderDocument> findByParentId(Long parentId);

}
