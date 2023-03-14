package com.compass.mspayment.service;

import com.compass.mspayment.domain.dto.form.SkuFormDto;
import com.compass.mspayment.domain.dto.form.SkuUpdateFormDto;

public interface SkuService {

    void save(SkuFormDto body);

    void update(Long id, SkuUpdateFormDto body);

    void delete(Long id);

}
