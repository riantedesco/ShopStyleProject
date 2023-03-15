package com.compass.mspayment.service;

import com.compass.mspayment.domain.dto.PaymentDto;
import com.compass.mspayment.domain.dto.form.PaymentFormDto;

import java.util.List;

public interface PaymentService {

    void save(PaymentFormDto body);

    List<PaymentDto> list();

    void update(Long id, PaymentFormDto body);

    void delete(Long id);

}
