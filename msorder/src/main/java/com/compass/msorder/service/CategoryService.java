package com.compass.msorder.service;

import com.compass.msorder.domain.dto.CategoryDto;
import com.compass.msorder.domain.dto.CategoryWithProductsDto;
import com.compass.msorder.domain.dto.form.CategoryFormDto;

import java.util.List;

public interface CategoryService {

    void save(CategoryFormDto body);

    List<CategoryDto> list();

    CategoryWithProductsDto find(Long id);

    void update(Long id, CategoryFormDto body);

    void delete(Long id);

}
