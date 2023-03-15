package com.compass.mscustomer.service;

import com.compass.mscustomer.domain.dto.CustomerDto;
import com.compass.mscustomer.domain.dto.form.CustomerFormDto;
import com.compass.mscustomer.domain.dto.form.CustomerPasswordUpdateFormDto;
import com.compass.mscustomer.domain.dto.form.CustomerUpdateFormDto;

public interface CustomerService {

    void save(CustomerFormDto body);

    CustomerDto find(Long id);

    void update(Long id, CustomerUpdateFormDto body);

    void updatePassword(Long id, CustomerPasswordUpdateFormDto body);

}
