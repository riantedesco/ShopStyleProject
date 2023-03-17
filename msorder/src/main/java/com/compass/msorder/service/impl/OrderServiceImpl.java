package com.compass.msorder.service.impl;

import com.compass.msorder.domain.OrderDocument;
import com.compass.msorder.domain.dto.OrderDto;
import com.compass.msorder.domain.dto.form.OrderFormDto;
import com.compass.msorder.publisher.mspayment.PaymentPublisher;
import com.compass.msorder.repository.OrderRepository;
import com.compass.msorder.service.OrderService;
import com.compass.msorder.utils.constants.StatusOrderOption;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private PaymentPublisher paymentPublisher;

	@Autowired
	private ModelMapper mapper;

	@Override
	public void save(OrderFormDto body) {
		mapper.getConfiguration().setAmbiguityIgnored(true);
		OrderDocument order = mapper.map(body, OrderDocument.class);
		this.orderRepository.save(order);
		paymentPublisher.publishPayment(order);
	}

	@Override
	public List<OrderDto> list(String startDate, String endDate, StatusOrderOption status) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

		LocalDateTime formattedStartDate = LocalDateTime.parse(startDate, formatter);
		LocalDateTime formattedEndDate = LocalDateTime.parse(endDate, formatter);

		List<OrderDto> orders = this.orderRepository.listWithStartDate(formattedStartDate).stream().map(o -> mapper.map(o, OrderDto.class))
				.collect(Collectors.toList());

		if (endDate != null) {
			orders = this.orderRepository.listWithStartDateAndEndDate(formattedStartDate, formattedEndDate).stream().map(o -> mapper.map(o, OrderDto.class))
					.collect(Collectors.toList());
		}

		if (status != null) {
			orders = this.orderRepository.listWithStartDateEndDateAndStatus(formattedStartDate, formattedEndDate, status).stream().map(o -> mapper.map(o, OrderDto.class))
					.collect(Collectors.toList());
		}

		return orders;
	}

	@Override
	public List<OrderDto> listByCustomerId(Long customerId, String startDate, String endDate, StatusOrderOption status) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

		LocalDateTime formattedStartDate = LocalDateTime.parse(startDate, formatter);
		LocalDateTime formattedEndDate = LocalDateTime.parse(endDate, formatter);

		List<OrderDto> orders = this.orderRepository.findByCustomerId(customerId).stream().map(o -> mapper.map(o, OrderDto.class))
				.collect(Collectors.toList());

		if (startDate != null) {
			orders = this.orderRepository.findByCustomerIdWithStartDate(customerId, formattedStartDate).stream().map(o -> mapper.map(o, OrderDto.class))
					.collect(Collectors.toList());
		}

		if (endDate != null) {
			orders = this.orderRepository.findByCustomerIdWithStartDateAndEndDate(customerId, formattedStartDate, formattedEndDate).stream().map(o -> mapper.map(o, OrderDto.class))
					.collect(Collectors.toList());
		}

		if (status != null) {
			orders = this.orderRepository.findByCustomerIdWithStartDateEndDateAndStatus(customerId, formattedStartDate, formattedEndDate, status).stream().map(o -> mapper.map(o, OrderDto.class))
					.collect(Collectors.toList());
		}

		return orders;
	}

}
