package com.compass.msbffshop.service;

import com.compass.msbffshop.domain.dto.form.SkuFormDto;
import com.compass.msbffshop.domain.dto.form.SkuUpdateFormDto;

public interface SkuService {

    void save(SkuFormDto body);

    void update(Long id, SkuUpdateFormDto body);

    void delete(Long id);

}
