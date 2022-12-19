package com.compass.mscustomer.fixture;

import com.compass.mscustomer.domain.AddressEntity;
import com.compass.mscustomer.domain.dto.CityDto;
import com.compass.mscustomer.domain.dto.form.CityFormDto;
import com.compass.mscustomer.util.constants.StateCityOption;

public class CityFixture {

    public static CityFormDto getCityFormDto() {
        return CityFormDto.builder()
                .name("City test")
                .state(StateCityOption.RIO_GRANDE_DO_SUL)
                .build();
    }

    public static CityDto getCityDto() {
        return CityDto.builder()
                .id(1L)
                .name("City test")
                .state(StateCityOption.RIO_GRANDE_DO_SUL)
                .build();
    }

    public static AddressEntity getCityEntity() {
        return AddressEntity.builder()
                .id(1L)
                .name("City test")
                .state(StateCityOption.RIO_GRANDE_DO_SUL)
                .build();
    }

    public static CityFormDto getCityFormDtoWithInvalidAttribute() {
        return CityFormDto.builder()
                .name("A")
                .state(StateCityOption.RIO_GRANDE_DO_SUL)
                .build();
    }

}
