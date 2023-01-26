package com.compass.mscatalog.service;

import com.compass.mscatalog.domain.dto.CategoryDto;
import com.compass.mscatalog.domain.dto.CategoryWithProductsDto;
import com.compass.mscatalog.domain.dto.form.CategoryFormDto;

import java.util.List;

public interface CategoryService {

    void save(CategoryFormDto body);

    List<CategoryDto> list();

    CategoryWithProductsDto find(Long id);

    void update(Long id, CategoryFormDto body);

    void delete(Long id);

}
