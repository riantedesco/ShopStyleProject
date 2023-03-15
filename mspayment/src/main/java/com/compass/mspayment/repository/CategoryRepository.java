package com.compass.mspayment.repository;

import com.compass.mspayment.domain.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<PaymentEntity, Long> {

    @Query("SELECT c FROM CategoryEntity c WHERE c.parent = null")
    List<PaymentEntity> findAllWhereParentIsNull();

    @Query("SELECT c FROM CategoryEntity c WHERE c.parent.id = :parentId")
    List<PaymentEntity> findByParentId(Long parentId);

}
