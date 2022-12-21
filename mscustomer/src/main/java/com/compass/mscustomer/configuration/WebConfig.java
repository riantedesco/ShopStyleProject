package com.compass.mscustomer.configuration;

import com.compass.mscustomer.util.converter.SexCustomerOptionConverter;
import com.compass.mscustomer.util.converter.StateAddressOptionConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StateAddressOptionConverter());
        registry.addConverter(new SexCustomerOptionConverter());
    }
}
