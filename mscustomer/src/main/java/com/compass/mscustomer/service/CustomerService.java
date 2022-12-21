package com.compass.mscustomer.service;

import com.compass.mscustomer.domain.dto.CustomerDto;
import com.compass.mscustomer.domain.dto.CustomerUpdateDto;
import com.compass.mscustomer.domain.dto.form.CustomerFormDto;
import com.compass.mscustomer.domain.dto.form.CustomerPasswordUpdateFormDto;
import com.compass.mscustomer.domain.dto.form.CustomerUpdateFormDto;

public interface CustomerService {

    CustomerDto save(CustomerFormDto body);

    CustomerDto find(Long id);

    CustomerUpdateDto update(Long id, CustomerUpdateFormDto body);

    CustomerUpdateDto updatePassword(Long id, CustomerPasswordUpdateFormDto body);

}
