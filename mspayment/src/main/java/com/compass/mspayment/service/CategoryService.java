package com.compass.mspayment.service;

import com.compass.mspayment.domain.dto.CategoryDto;
import com.compass.mspayment.domain.dto.CategoryWithProductsDto;
import com.compass.mspayment.domain.dto.form.CategoryFormDto;

import java.util.List;

public interface CategoryService {

    void save(CategoryFormDto body);

    List<CategoryDto> list();

    CategoryWithProductsDto find(Long id);

    void update(Long id, CategoryFormDto body);

    void delete(Long id);

}
