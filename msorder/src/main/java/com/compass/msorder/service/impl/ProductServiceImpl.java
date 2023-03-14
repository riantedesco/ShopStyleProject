package com.compass.msorder.service.impl;

import com.compass.msorder.domain.CategoryEntity;
import com.compass.msorder.domain.ProductEntity;
import com.compass.msorder.domain.SkuEntity;
import com.compass.msorder.domain.dto.ProductDto;
import com.compass.msorder.domain.dto.SkuDto;
import com.compass.msorder.domain.dto.form.ProductFormDto;
import com.compass.msorder.domain.dto.form.ProductUpdateFormDto;
import com.compass.msorder.exception.InvalidAttributeException;
import com.compass.msorder.exception.NotFoundAttributeException;
import com.compass.msorder.repository.CategoryRepository;
import com.compass.msorder.repository.ProductRepository;
import com.compass.msorder.repository.SkuRepository;
import com.compass.msorder.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private SkuRepository skuRepository;

	@Autowired
	private ModelMapper mapper;

	@Override
	public void save(ProductFormDto body) {
		mapper.getConfiguration().setAmbiguityIgnored(true);
		ProductEntity product = mapper.map(body, ProductEntity.class);

		if(body.getCategoryId() != null) {
			product.setId(null);
			Optional<CategoryEntity> category = this.categoryRepository.findById(body.getCategoryId());
			if(!category.isPresent()) {
				throw new InvalidAttributeException("Category not found");
			}
			if(category.get().getActive().equals(false)) {
				throw new InvalidAttributeException("Category is inactive");
			}
			product.setCategory(category.get());
		}

		this.productRepository.save(product);
	}

	@Override
	public List<ProductDto> list() {
		List<ProductDto> productsDto = this.productRepository.findAll().stream().map(p -> mapper.map(p, ProductDto.class))
				.collect(Collectors.toList());
		return productsDto;
	}

	@Override
	public ProductDto find(Long id) {
		Optional<ProductEntity> product = this.productRepository.findById(id);
		if (!product.isPresent()) {
			throw new NotFoundAttributeException("Product not found");
		}

		List<SkuEntity> skus = this.skuRepository.findByProductId(id);

		ProductDto productDtoResponse = mapper.map(product.get(), ProductDto.class);
		List<SkuDto> skuDtoList = new ArrayList<>();
		for (SkuEntity sku: skus) {
			SkuDto skuDtoResponse = mapper.map(sku, SkuDto.class);
			skuDtoList.add(skuDtoResponse);
		}

		productDtoResponse.setSkus(skuDtoList);

		return productDtoResponse;
	}

	@Override
	public void update(Long id, ProductUpdateFormDto body) {
		Optional<ProductEntity> product = this.productRepository.findById(id);
		if (!product.isPresent()) {
			throw new NotFoundAttributeException("Product not found");
		}

		product.get().setName(body.getName());
		product.get().setDescription(body.getDescription());
		product.get().setBrand(body.getBrand());
		product.get().setMaterial(body.getMaterial());
		product.get().setActive(body.getActive());

		this.productRepository.save(product.get());
	}

	@Override
	public void delete(Long id) {
		Optional<ProductEntity> product = this.productRepository.findById(id);
		if (!product.isPresent()) {
			throw new NotFoundAttributeException("Product not found");
		}

		this.productRepository.deleteById(id);
	}

}
