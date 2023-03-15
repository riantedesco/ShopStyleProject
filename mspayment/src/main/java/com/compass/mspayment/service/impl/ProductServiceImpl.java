package com.compass.mspayment.service.impl;

import com.compass.mspayment.domain.PaymentEntity;
import com.compass.mspayment.domain.InstallmentEntity;
import com.compass.mspayment.domain.dto.ProductDto;
import com.compass.mspayment.domain.dto.SkuDto;
import com.compass.mspayment.domain.dto.form.ProductFormDto;
import com.compass.mspayment.domain.dto.form.ProductUpdateFormDto;
import com.compass.mspayment.exception.InvalidAttributeException;
import com.compass.mspayment.exception.NotFoundAttributeException;
import com.compass.mspayment.repository.CategoryRepository;
import com.compass.mspayment.repository.ProductRepository;
import com.compass.mspayment.repository.SkuRepository;
import com.compass.mspayment.service.ProductService;
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
		InstallmentEntity product = mapper.map(body, InstallmentEntity.class);

		if(body.getCategoryId() != null) {
			product.setId(null);
			Optional<PaymentEntity> category = this.categoryRepository.findById(body.getCategoryId());
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
		Optional<InstallmentEntity> product = this.productRepository.findById(id);
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
		Optional<InstallmentEntity> product = this.productRepository.findById(id);
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
		Optional<InstallmentEntity> product = this.productRepository.findById(id);
		if (!product.isPresent()) {
			throw new NotFoundAttributeException("Product not found");
		}

		this.productRepository.deleteById(id);
	}

}
