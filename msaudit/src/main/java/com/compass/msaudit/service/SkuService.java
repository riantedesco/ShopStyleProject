package com.compass.msaudit.service;

import com.compass.msaudit.domain.dto.form.SkuFormDto;
import com.compass.msaudit.domain.dto.form.SkuUpdateFormDto;

public interface SkuService {

    void save(SkuFormDto body);

    void update(Long id, SkuUpdateFormDto body);

    void delete(Long id);

}
