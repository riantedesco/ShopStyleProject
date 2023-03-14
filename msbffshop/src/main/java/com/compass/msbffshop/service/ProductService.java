package com.compass.msbffshop.service;

import com.compass.msbffshop.domain.dto.ProductDto;
import com.compass.msbffshop.domain.dto.form.ProductFormDto;
import com.compass.msbffshop.domain.dto.form.ProductUpdateFormDto;

import java.util.List;

public interface ProductService {

    void save(ProductFormDto body);

    List<ProductDto> list();

    ProductDto find(Long id);

    void update(Long id, ProductUpdateFormDto body);

    void delete(Long id);

}
