package com.compass.mscatalog.repository;

import com.compass.mscatalog.domain.MediaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaRepository extends JpaRepository<MediaEntity, Long> {

}
