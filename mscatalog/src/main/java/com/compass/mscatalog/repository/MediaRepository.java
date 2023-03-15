package com.compass.mscatalog.repository;

import com.compass.mscatalog.domain.MediaEntity;
import com.compass.mscatalog.domain.SkuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.print.attribute.standard.Media;
import java.util.List;

public interface MediaRepository extends JpaRepository<MediaEntity, Long> {

    @Query("SELECT m FROM MediaEntity m WHERE m.sku.id = :skuId")
    List<MediaEntity> findBySkuId(Long skuId);
}
