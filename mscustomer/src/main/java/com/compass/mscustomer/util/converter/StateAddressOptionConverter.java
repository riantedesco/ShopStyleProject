package com.compass.mscustomer.util.converter;

import com.compass.mscustomer.exception.NotFoundAttributeException;
import com.compass.mscustomer.util.constants.StateAddressOption;
import org.springframework.core.convert.converter.Converter;

public class StateAddressOptionConverter implements Converter<String, StateAddressOption> {

    @Override
    public StateAddressOption convert(String state) {
        try {
            return StateAddressOption.valueOf(state.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new NotFoundAttributeException("No cities found");
        }
    }
}
