package com.compass.mscustomer.util.validation;

import com.compass.mscustomer.domain.AddressEntity;
import com.compass.mscustomer.domain.CustomerEntity;
import com.compass.mscustomer.util.validation.save.AddressSaveValidation;
import com.compass.mscustomer.util.validation.save.CustomerSaveValidation;
import com.compass.mscustomer.util.validation.update.AddressUpdateValidation;
import com.compass.mscustomer.util.validation.update.CustomerUpdateValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Validation {

	@Autowired
	private CustomerSaveValidation customerSaveValidation;

	@Autowired
	private CustomerUpdateValidation customerUpdateValidation;

	@Autowired
	private AddressSaveValidation addressSaveValidation;

	@Autowired
	private AddressUpdateValidation addressUpdateValidation;

	public void validateSaveCustomer (CustomerEntity customer) {
	}

	public void validateUpdateCustomer (CustomerEntity customer) {
	}

	public void validatePasswordUpdateCustomer (CustomerEntity customer) {
	}

	public void validateSaveAddress (AddressEntity address) {
	}

	public void validateUpdateAddress (AddressEntity address) {
	}
}
