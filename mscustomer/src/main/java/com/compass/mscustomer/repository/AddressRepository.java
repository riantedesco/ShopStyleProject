package com.compass.mscustomer.repository;

import com.compass.mscustomer.domain.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<AddressEntity, Long> {

    @Query("SELECT a FROM AddressEntity a WHERE a.customer_id = :customerId")
    List<AddressEntity> findByCustomerId(Long customerId);

}
