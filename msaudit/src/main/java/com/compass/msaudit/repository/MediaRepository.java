package com.compass.msaudit.repository;

import com.compass.msaudit.domain.MediaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaRepository extends JpaRepository<MediaEntity, Long> {

}
