package com.compass.mscustomer.util.validation.customer;

import com.compass.mscustomer.domain.CustomerEntity;
import com.compass.mscustomer.exception.InvalidAttributeException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class SexCustomerValidation {

	private String masc = "Masculino";
	private String fem = "Feminino";

	List<String> sexList = Arrays.asList(masc, fem);

	public void validateSex (CustomerEntity customer) {
		String sex = customer.getSex();
		if (!sexList.contains(sex)) {
			throw new InvalidAttributeException("Sex  " + sex + " is invalid");
		}
	}

}
