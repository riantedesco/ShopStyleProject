package com.compass.msbffshop.service.impl;

import com.compass.msbffshop.domain.MediaEntity;
import com.compass.msbffshop.domain.ProductEntity;
import com.compass.msbffshop.domain.SkuEntity;
import com.compass.msbffshop.domain.dto.form.SkuFormDto;
import com.compass.msbffshop.domain.dto.form.SkuUpdateFormDto;
import com.compass.msbffshop.exception.InvalidAttributeException;
import com.compass.msbffshop.exception.NotFoundAttributeException;
import com.compass.msbffshop.repository.ProductRepository;
import com.compass.msbffshop.repository.SkuRepository;
import com.compass.msbffshop.service.SkuService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SkuServiceImpl implements SkuService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private SkuRepository skuRepository;

	@Autowired
	private ModelMapper mapper;

	@Override
	public void save(SkuFormDto body) {
		mapper.getConfiguration().setAmbiguityIgnored(true);
		SkuEntity sku = mapper.map(body, SkuEntity.class);

		if(body.getProductId() != null) {
			sku.setId(null);
			Optional<ProductEntity> product = this.productRepository.findById(body.getProductId());
			if(!product.isPresent()) {
				throw new InvalidAttributeException("Product not found");
			}
			sku.setProduct(product.get());
		}

		this.skuRepository.save(sku);

		for (String image: body.getImages()) {
			MediaEntity media = new MediaEntity();
			media.setImageUrl(image);
			media.setSku(sku);
		}
	}

	@Override
	public void update(Long id, SkuUpdateFormDto body) {
		Optional<SkuEntity> sku = this.skuRepository.findById(id);
		if (!sku.isPresent()) {
			throw new NotFoundAttributeException("Sku not found");
		}

		sku.get().setPrice(body.getPrice());
		sku.get().setQuantity(body.getQuantity());
		sku.get().setColor(body.getColor());
		sku.get().setSize(body.getSize());
		sku.get().setHeight(body.getHeight());
		sku.get().setWidth(body.getWidth());

		this.skuRepository.save(sku.get());
	}

	@Override
	public void delete(Long id) {
		Optional<SkuEntity> sku = this.skuRepository.findById(id);
		if (!sku.isPresent()) {
			throw new NotFoundAttributeException("Sku not found");
		}

		this.skuRepository.deleteById(id);
	}

}
