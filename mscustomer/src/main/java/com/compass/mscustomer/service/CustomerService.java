package com.compass.mscustomer.service;

import com.compass.mscustomer.domain.dto.CustomerDto;
import com.compass.mscustomer.domain.dto.form.CustomerFormDto;
import com.compass.mscustomer.domain.dto.form.CustomerPasswordUpdateFormDto;
import com.compass.mscustomer.domain.dto.form.CustomerUpdateFormDto;

public interface CustomerService {

    CustomerDto save(CustomerFormDto body);

    CustomerDto find(Long id);

    CustomerDto update(Long id, CustomerUpdateFormDto body);

    CustomerDto updatePassword(Long id, CustomerPasswordUpdateFormDto body);

}
