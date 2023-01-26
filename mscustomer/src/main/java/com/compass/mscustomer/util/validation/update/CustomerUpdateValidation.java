package com.compass.mscustomer.util.validation.update;

import com.compass.mscustomer.domain.CustomerEntity;
import com.compass.mscustomer.exception.InvalidAttributeException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@Service
public class CustomerUpdateValidation {

//    public void validateSex (CustomerEntity customer) {
//        String masculino = "Masculino";
//        String feminino = "Feminino";
//        List<String> list = Arrays.asList(masculino, feminino);
//        if (!list.contains(customer.getSex())) {
//            throw new InvalidAttributeException("Invalid sex");
//        }
//    }

}
