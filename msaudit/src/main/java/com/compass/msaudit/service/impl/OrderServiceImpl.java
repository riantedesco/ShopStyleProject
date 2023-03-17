package com.compass.msaudit.service.impl;

import com.compass.msaudit.domain.OrderDocument;
import com.compass.msaudit.domain.dto.form.OrderFormDto;
import com.compass.msaudit.exception.InvalidAttributeException;
import com.compass.msaudit.exception.NotFoundAttributeException;
import com.compass.msaudit.repository.OrderRepository;
import com.compass.msaudit.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ModelMapper mapper;

	@Override
	public void save(OrderFormDto body) {
		mapper.getConfiguration().setAmbiguityIgnored(true);
		OrderDocument order = mapper.map(body, OrderDocument.class);

		this.orderRepository.save(order);
	}

	@Override
	public List<CategoryDto> list() {
		List<CategoryDto> categoriesDto = this.orderRepository.findAll().stream().map(c -> mapper.map(c, CategoryDto.class))
				.collect(Collectors.toList());
		List<CategoryDto> categoriesResponse = new ArrayList<>();
		for (CategoryDto categoryDto: categoriesDto) {
				Optional<OrderDocument> category = this.orderRepository.findById(categoryDto.getId());
//			if (category.get().getParent() == null) {
				List<CategoryDto> children = this.orderRepository.findByParentId(category.get().getId()).stream().map(c -> mapper.map(c, CategoryDto.class))
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
		Optional<OrderDocument> category = this.orderRepository.findById(id);
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
		Optional<OrderDocument> category = this.orderRepository.findById(id);
		if (!category.isPresent()) {
			throw new NotFoundAttributeException("Category not found");
		}

		category.get().setName(body.getName());
		category.get().setActive(body.getActive());

		if (body.getParentId() != null) {
			Optional<OrderDocument> parent = this.orderRepository.findById(body.getParentId());
			if (!parent.isPresent()) {
				throw new InvalidAttributeException("Parent not found");
			}
			if (parent.get().getActive().equals(false)) {
				throw new InvalidAttributeException("Parent is inactive");
			}
			category.get().setParent(parent.get());
		}

		this.orderRepository.save(category.get());
	}

	@Override
	public void delete(Long id) {
		Optional<OrderDocument> category = this.orderRepository.findById(id);
		if (!category.isPresent()) {
			throw new NotFoundAttributeException("Category not found");
		}

		this.orderRepository.deleteById(id);
	}

}
