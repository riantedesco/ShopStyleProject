package com.compass.msbffshop.repository;

import com.compass.msbffshop.domain.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    @Query("SELECT c FROM CategoryEntity c WHERE c.parent = null")
    List<CategoryEntity> findAllWhereParentIsNull();

    @Query("SELECT c FROM CategoryEntity c WHERE c.parent.id = :parentId")
    List<CategoryEntity> findByParentId(Long parentId);

}
