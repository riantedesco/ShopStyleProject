package com.compass.msbffshop.service;

import com.compass.msbffshop.domain.dto.CategoryDto;
import com.compass.msbffshop.domain.dto.CategoryWithProductsDto;
import com.compass.msbffshop.domain.dto.form.CategoryFormDto;

import java.util.List;

public interface CategoryService {

    void save(CategoryFormDto body);

    List<CategoryDto> list();

    CategoryWithProductsDto find(Long id);

    void update(Long id, CategoryFormDto body);

    void delete(Long id);

}
