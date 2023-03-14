package com.compass.msorder.service;

import com.compass.msorder.domain.dto.form.SkuFormDto;
import com.compass.msorder.domain.dto.form.SkuUpdateFormDto;

public interface SkuService {

    void save(SkuFormDto body);

    void update(Long id, SkuUpdateFormDto body);

    void delete(Long id);

}
