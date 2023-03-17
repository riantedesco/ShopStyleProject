package com.compass.mscatalog.service.impl;

import com.compass.mscatalog.domain.CategoryEntity;
import com.compass.mscatalog.domain.dto.CategoryDto;
import com.compass.mscatalog.domain.dto.CategoryWithProductsDto;
import com.compass.mscatalog.domain.dto.ProductDto;
import com.compass.mscatalog.domain.dto.form.CategoryFormDto;
import com.compass.mscatalog.exception.InvalidAttributeException;
import com.compass.mscatalog.exception.NotFoundAttributeException;
import com.compass.mscatalog.repository.CategoryRepository;
import com.compass.mscatalog.repository.ProductRepository;
import com.compass.mscatalog.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
				throw new InvalidAttributeException("Parent " + body.getParentId() + " not found");
			}
			if(parent.get().getActive().equals(false)) {
				throw new InvalidAttributeException("Parent " + body.getParentId() + " is inactive");
			}
			category.setParent(parent.get());
		}

		this.categoryRepository.save(category);
	}

	@Override
	public List<CategoryDto> list() {
		List<CategoryDto> categories = this.categoryRepository.findAll()
				.stream().map(c -> mapper.map(c, CategoryDto.class))
				.collect(Collectors.toList());
		return categories;
	}

	@Override
	public CategoryWithProductsDto find(Long id) {
		Optional<CategoryEntity> category = this.categoryRepository.findById(id);
		if (!category.isPresent()) {
			throw new NotFoundAttributeException("Category " + id + " not found");
		}

		CategoryWithProductsDto categoryWithProductsDtoResponse = mapper.map(category.get(),
				CategoryWithProductsDto.class);

		List<ProductDto> products = this.productRepository.findByCategoryId(id)
				.stream().map(p -> mapper.map(p, ProductDto.class))
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
			throw new NotFoundAttributeException("Category " + id + " not found");
		}

		category.get().setName(body.getName());
		category.get().setActive(body.getActive());

		if (body.getParentId() != null) {
			Optional<CategoryEntity> parent = this.categoryRepository.findById(body.getParentId());
			if (!parent.isPresent()) {
				throw new InvalidAttributeException("Parent " + body.getParentId() + " not found");
			}
			if (parent.get().getActive().equals(false)) {
				throw new InvalidAttributeException("Parent " + body.getParentId() + " is inactive");
			}
			category.get().setParent(parent.get());
		}

		this.categoryRepository.save(category.get());
	}

	@Override
	public void delete(Long id) {
		Optional<CategoryEntity> category = this.categoryRepository.findById(id);
		if (!category.isPresent()) {
			throw new NotFoundAttributeException("Category " + id + " not found");
		}

		this.categoryRepository.deleteById(id);
	}

}
