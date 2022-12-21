package com.compass.mscustomer.service;

import com.compass.mscustomer.domain.dto.AddressDto;
import com.compass.mscustomer.domain.dto.form.AddressFormDto;
import com.compass.mscustomer.domain.dto.form.AddressUpdateFormDto;

public interface AddressService {

    AddressDto save(AddressFormDto body);

    AddressDto update(Long id, AddressUpdateFormDto body);

    void delete(Long id);

}
