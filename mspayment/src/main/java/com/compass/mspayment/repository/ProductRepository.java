package com.compass.mspayment.repository;

import com.compass.mspayment.domain.InstallmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<InstallmentEntity, Long> {

    @Query("SELECT p FROM ProductEntity p WHERE p.category.id = :categoryId")
    List<InstallmentEntity> findByCategoryId(Long categoryId);

}
