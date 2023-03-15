package com.compass.mscatalog.service;

import com.compass.mscatalog.domain.dto.ProductDto;
import com.compass.mscatalog.domain.dto.ProductWithSkusDto;
import com.compass.mscatalog.domain.dto.form.ProductFormDto;
import com.compass.mscatalog.domain.dto.form.ProductUpdateFormDto;

import java.util.List;

public interface ProductService {

    void save(ProductFormDto body);

    List<ProductDto> list();

    ProductWithSkusDto find(Long id);

    void update(Long id, ProductUpdateFormDto body);

    void delete(Long id);

}
