package com.compass.msorder.utils.converter;

import com.compass.msorder.exception.NotFoundAttributeException;
import com.compass.msorder.utils.constants.StatusOrderOption;
import org.springframework.core.convert.converter.Converter;

public class StatusOrderPublisherOptionConverter implements Converter<String, StatusOrderOption> {

    @Override
    public StatusOrderOption convert(String status) {
        try {
            return StatusOrderOption.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new NotFoundAttributeException("No status found");
        }
    }
}
