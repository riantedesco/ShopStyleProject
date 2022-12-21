package com.compass.mscustomer.util.validation.update;

import com.compass.mscustomer.domain.CustomerEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class CustomerUpdateValidation {

//	public void validateSex (CustomerEntity client) {
//		String masculino = "Masculino";
//		String feminino = "Feminino";
//		List<String> list = Arrays.asList(masculino, feminino);
//		if (!list.contains(client.getSex())) {
//			throw new InvalidAttributeException("Invalid sex");
//		}
//	}

	public void validateBirthdate (CustomerEntity customer) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String formattedDate = customer.getBirthdate().format(formatter);
		LocalDate localDateBirthdate = LocalDate.parse(formattedDate, formatter);
		customer.setBirthdate(localDateBirthdate);
	}

}
