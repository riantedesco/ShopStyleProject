package com.compass.msorder.service;

import com.compass.msorder.domain.dto.OrderDto;
import com.compass.msorder.domain.dto.form.OrderFormDto;
import com.compass.msorder.utils.constants.StatusOrderOption;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface OrderService {

    void save(OrderFormDto body);

    List<OrderDto> list(String startDate, String endDate, StatusOrderOption status);

    List<OrderDto> listByCustomer(Long customerId);

}
