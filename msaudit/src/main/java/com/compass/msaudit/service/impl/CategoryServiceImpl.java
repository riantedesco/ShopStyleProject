package com.compass.msaudit.service.impl;

import com.compass.msaudit.domain.CategoryEntity;
import com.compass.msaudit.domain.dto.CategoryDto;
import com.compass.msaudit.domain.dto.CategoryWithProductsDto;
import com.compass.msaudit.domain.dto.ProductDto;
import com.compass.msaudit.domain.dto.form.CategoryFormDto;
import com.compass.msaudit.exception.InvalidAttributeException;
import com.compass.msaudit.exception.NotFoundAttributeException;
import com.compass.msaudit.repository.CategoryRepository;
import com.compass.msaudit.repository.ProductRepository;
import com.compass.msaudit.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ModelMapper mapper;

	@Override
	public void save(CategoryFormDto body) {
		mapper.getConfiguration().setAmbiguityIgnored(true);
		CategoryEntity category = mapper.map(body, CategoryEntity.class);

		if(body.getParentId() != null) {
			category.setId(null);
			Optional<CategoryEntity> parent = this.categoryRepository.findById(body.getParentId());
			if(!parent.isPresent()) {
				throw new InvalidAttributeException("Parent not found");
			}
			if(parent.get().getActive().equals(false)) {
				throw new InvalidAttributeException("Parent is inactive");
			}
			category.setParent(parent.get());
		}

		this.categoryRepository.save(category);
	}

	@Override
	public List<CategoryDto> list() {
		List<CategoryDto> categoriesDto = this.categoryRepository.findAll().stream().map(c -> mapper.map(c, CategoryDto.class))
				.collect(Collectors.toList());
		List<CategoryDto> categoriesResponse = new ArrayList<>();
		for (CategoryDto categoryDto: categoriesDto) {
				Optional<CategoryEntity> category = this.categoryRepository.findById(categoryDto.getId());
//			if (category.get().getParent() == null) {
				List<CategoryDto> children = this.categoryRepository.findByParentId(category.get().getId()).stream().map(c -> mapper.map(c, CategoryDto.class))
						.collect(Collectors.toList());
				categoryDto.setChildren(children);
//			}
			if (category.get().getParent() == null) {
				categoriesResponse.add(categoryDto);
			}

		}
		return categoriesResponse;
	}

	@Override
	public CategoryWithProductsDto find(Long id) {
		Optional<CategoryEntity> category = this.categoryRepository.findById(id);
		if (!category.isPresent()) {
			throw new NotFoundAttributeException("Category not found");
		}

		CategoryWithProductsDto categoryWithProductsDtoResponse = mapper.map(category.get(), CategoryWithProductsDto.class);

		List<ProductDto> products = this.productRepository.findByCategoryId(id).stream().map(p -> mapper.map(p, ProductDto.class))
				.collect(Collectors.toList());
		if (!products.isEmpty()) {
			categoryWithProductsDtoResponse.setProducts(products);
		}
		return categoryWithProductsDtoResponse;

	}

	@Override
	public void update(Long id, CategoryFormDto body) {
		Optional<CategoryEntity> category = this.categoryRepository.findById(id);
		if (!category.isPresent()) {
			throw new NotFoundAttributeException("Category not found");
		}

		category.get().setName(body.getName());
		category.get().setActive(body.getActive());

		if (body.getParentId() != null) {
			Optional<CategoryEntity> parent = this.categoryRepository.findById(body.getParentId());
			if (!parent.isPresent()) {
				throw new InvalidAttributeException("Parent not found");
			}
			if (parent.get().getActive().equals(false)) {
				throw new InvalidAttributeException("Parent is inactive");
			}
			category.get().setParent(parent.get());
		}

		this.categoryRepository.save(category.get());
	}

	@Override
	public void delete(Long id) {
		Optional<CategoryEntity> category = this.categoryRepository.findById(id);
		if (!category.isPresent()) {
			throw new NotFoundAttributeException("Category not found");
		}

		this.categoryRepository.deleteById(id);
	}

}
