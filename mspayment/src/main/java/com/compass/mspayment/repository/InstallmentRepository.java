package com.compass.mspayment.repository;

import com.compass.mspayment.domain.InstallmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InstallmentRepository extends JpaRepository<InstallmentEntity, Long> {

    @Query("SELECT i FROM InstallmentEntity i WHERE i.payment.id = :paymentId")
    List<InstallmentEntity> findByPaymentId(Long paymentId);

}
