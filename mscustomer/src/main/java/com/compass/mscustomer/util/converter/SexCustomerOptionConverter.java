package com.compass.mscustomer.util.converter;

import com.compass.mscustomer.exception.NotFoundAttributeException;
import com.compass.mscustomer.util.constants.SexCustomerOption;
import org.springframework.core.convert.converter.Converter;

public class SexCustomerOptionConverter implements Converter<String, SexCustomerOption> {

    @Override
    public SexCustomerOption convert(String sex) {
        try {
            return SexCustomerOption.valueOf(sex.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new NotFoundAttributeException("No sexes found");
        }
    }
}
