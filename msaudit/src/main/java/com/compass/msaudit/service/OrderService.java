package com.compass.msaudit.service;

import com.compass.msaudit.domain.dto.OrderDto;
import com.compass.msaudit.domain.dto.form.OrderFormDto;

import java.util.List;

public interface OrderService {

    void save(OrderFormDto body);

    List<OrderDto> list();

    List<OrderDto> listByCustomer(Long id);

}
