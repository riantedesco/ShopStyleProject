package com.compass.mscustomer.service;

import com.compass.mscustomer.domain.AddressEntity;
import com.compass.mscustomer.domain.dto.CityDto;
import com.compass.mscustomer.exception.NotFoundAttributeException;
import com.compass.mscustomer.fixture.CityFixture;
import com.compass.mscustomer.repository.CityRepository;
import com.compass.mscustomer.service.impl.CityServiceImpl;
import com.compass.mscustomer.util.validation.Validation;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@AutoConfigureTestDatabase
@RequiredArgsConstructor
public class CityServiceTest {

    @Mock
    private CityRepository cityRepository;

    @Spy
    private ModelMapper mapper;

    @Mock
    private Validation validation;

    @InjectMocks
    private CityServiceImpl cityService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save_WhenSendSave_ExpectedCity ()  {
        when(cityRepository.save(any(AddressEntity.class))).thenReturn(CityFixture.getCityEntity());
        CityDto response = cityService.save(CityFixture.getCityFormDto());

        verify(cityRepository, times(1)).save(any(AddressEntity.class));
        assertEquals(response.getId(), CityFixture.getCityDto().getId());
        assertNotNull(response);
    }

    @Test
    void findByName_WhenSendFindByNameWithExistingName_ExpectedCity ()  {
        when(cityRepository.findByName(anyString())).thenReturn(List.of(CityFixture.getCityEntity()));
        List<CityDto> response = cityService.findByName(CityFixture.getCityDto().getName());

        assertFalse(response.isEmpty());
    }

    @Test
    void findByName_WhenSendFindByNameNonExistingName_ExpectedNotFoundAttributeException ()  {
        NotFoundAttributeException response = assertThrows(NotFoundAttributeException.class, () -> cityService.findByName("Name blá blá blá"));

        assertNotNull(response);
        assertEquals("No cities found", response.getMessage());
    }

    @Test
    void findByState_WhenSendFindByStateWithExistingState_ExpectedCity ()  {
        when(cityRepository.findByState(any())).thenReturn(List.of(CityFixture.getCityEntity()));
        List<CityDto> response = cityService.findByState(CityFixture.getCityDto().getState());

        assertFalse(response.isEmpty());
    }

    @Test
    void findByState_WhenSendFindByStateWithNonExistingState_ExpectedNotFoundAttributeException ()  {
        NotFoundAttributeException response = assertThrows(NotFoundAttributeException.class, () -> cityService.findByState(any()));

        assertNotNull(response);
        assertEquals("No cities found", response.getMessage());
    }

}
