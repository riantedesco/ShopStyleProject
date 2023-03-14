package com.compass.msorder.repository;

import com.compass.msorder.domain.MediaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaRepository extends JpaRepository<MediaEntity, Long> {

}
