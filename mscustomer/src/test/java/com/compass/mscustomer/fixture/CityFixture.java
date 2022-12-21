package com.compass.mscustomer.fixture;

import com.compass.mscustomer.domain.AddressEntity;
import com.compass.mscustomer.domain.dto.AddressDto;
import com.compass.mscustomer.domain.dto.form.AddressFormDto;
import com.compass.mscustomer.util.constants.StateAddressOption;

public class CityFixture {

    public static AddressFormDto getCityFormDto() {
        return AddressFormDto.builder()
                .name("City test")
                .state(StateAddressOption.RIO_GRANDE_DO_SUL)
                .build();
    }

    public static AddressDto getCityDto() {
        return AddressDto.builder()
                .id(1L)
                .name("City test")
                .state(StateAddressOption.RIO_GRANDE_DO_SUL)
                .build();
    }

    public static AddressEntity getCityEntity() {
        return AddressEntity.builder()
                .id(1L)
                .name("City test")
                .state(StateAddressOption.RIO_GRANDE_DO_SUL)
                .build();
    }

    public static AddressFormDto getCityFormDtoWithInvalidAttribute() {
        return AddressFormDto.builder()
                .name("A")
                .state(StateAddressOption.RIO_GRANDE_DO_SUL)
                .build();
    }

}
