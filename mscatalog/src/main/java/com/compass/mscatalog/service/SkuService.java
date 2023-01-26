package com.compass.mscatalog.service;

import com.compass.mscatalog.domain.dto.form.SkuFormDto;
import com.compass.mscatalog.domain.dto.form.SkuUpdateFormDto;

public interface SkuService {

    void save(SkuFormDto body);

    void update(Long id, SkuUpdateFormDto body);

    void delete(Long id);

}
