package com.compass.mscustomer.util.validation;

import com.compass.mscustomer.domain.AddressEntity;
import com.compass.mscustomer.domain.CustomerEntity;
import com.compass.mscustomer.util.validation.address.StateAddressValidation;
import com.compass.mscustomer.util.validation.customer.SexCustomerValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Validation {

	@Autowired
	private SexCustomerValidation sexCustomerValidation;

	@Autowired
	private StateAddressValidation stateAddressValidation;

	public void validateCustomer (CustomerEntity client) {
		sexCustomerValidation.validateSex(client);
	}

	public void validateAddress (AddressEntity address) {
		stateAddressValidation.validateState(address);
	}

}
