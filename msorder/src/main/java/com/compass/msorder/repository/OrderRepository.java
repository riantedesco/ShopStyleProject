package com.compass.msorder.repository;

import com.compass.msorder.domain.OrderDocument;
import com.compass.msorder.utils.constants.StatusOrderOption;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends MongoRepository<OrderDocument, Long> {

    @Query("{ 'date' : { $gte: ?0 } }")
    List<OrderDocument> listWithStartDate(LocalDateTime startDate);

    @Query("{ 'date' : { $gte: ?0, $lte: ?1 } }")
    List<OrderDocument> listWithStartDateAndEndDate(LocalDateTime startDate, LocalDateTime endDate);

    @Query("{ 'date' : { $gte: ?0, $lte: ?1 }, 'status' : ?2 }")
    List<OrderDocument> listWithStartDateEndDateAndStatus(LocalDateTime startDate, LocalDateTime endDate, StatusOrderOption status);

    @Query("{ 'customer.id' : ?0 }")
    List<OrderDocument> findByCustomerId(Long customerId);

    @Query("{ 'customer.id' : ?0, 'date' : { $gte: ?1 } }")
    List<OrderDocument> findByCustomerIdWithStartDate(Long customerId, LocalDateTime startDate);

    @Query("{ 'customer.id' : ?0, 'date' : { $gte: ?1, $lte: ?2 } }")
    List<OrderDocument> findByCustomerIdWithStartDateAndEndDate(Long customerId, LocalDateTime startDate, LocalDateTime endDate);

    @Query("{ 'customer.id' : ?0, 'date' : { $gte: ?1, $lte: ?2 }, 'status' : ?3 }")
    List<OrderDocument> findByCustomerIdWithStartDateEndDateAndStatus(Long customerId, LocalDateTime startDate, LocalDateTime endDate, StatusOrderOption status);

}
