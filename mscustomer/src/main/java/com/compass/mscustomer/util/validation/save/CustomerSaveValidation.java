package com.compass.mscustomer.util.validation.save;

import com.compass.mscustomer.domain.CustomerEntity;
import com.compass.mscustomer.exception.InvalidAttributeException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@Service
public class CustomerSaveValidation {

//	public void validateSex (CustomerEntity customer) {
//		String masculino = "Masculino";
//		String feminino = "Feminino";
//		List<String> list = Arrays.asList(masculino, feminino);
//		if (!list.contains(customer.getSex())) {
//			throw new InvalidAttributeException("Invalid sex");
//		}
//	}

}
