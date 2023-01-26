package com.compass.mscustomer.service.impl;

import com.compass.mscustomer.domain.AddressEntity;
import com.compass.mscustomer.domain.CustomerEntity;
import com.compass.mscustomer.domain.dto.AddressDto;
import com.compass.mscustomer.domain.dto.form.AddressFormDto;
import com.compass.mscustomer.domain.dto.form.AddressUpdateFormDto;
import com.compass.mscustomer.exception.InvalidAttributeException;
import com.compass.mscustomer.exception.NotFoundAttributeException;
import com.compass.mscustomer.repository.AddressRepository;
import com.compass.mscustomer.repository.CustomerRepository;
import com.compass.mscustomer.service.AddressService;
import com.compass.mscustomer.util.validation.Validation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private Validation validation;

	@Override
	public void save(AddressFormDto body) {
		mapper.getConfiguration().setAmbiguityIgnored(true);
		AddressEntity address = mapper.map(body, AddressEntity.class);

		address.setId(null);
		Optional<CustomerEntity> customer = this.customerRepository.findById(body.getCustomerId());
		if(!customer.isPresent()) {
			throw new InvalidAttributeException("Customer not found");
		}
		address.setCustomer(customer.get());

		validation.validateSaveAddress(address);
		this.addressRepository.save(address);
	}

	@Override
	public void update(Long id, AddressUpdateFormDto body) {
		Optional<AddressEntity> address = this.addressRepository.findById(id);
		if (!address.isPresent()) {
			throw new NotFoundAttributeException("Address not found");
		}

		address.get().setState(body.getState());
		address.get().setCity(body.getCity());
		address.get().setDistrict(body.getDistrict());
		address.get().setStreet(body.getStreet());
		address.get().setNumber(body.getNumber());
		address.get().setCep(body.getCep());
		address.get().setComplement(body.getComplement());

		Optional<CustomerEntity> customer = this.customerRepository.findById(body.getCustomerId());
		if (!customer.isPresent()) {
			throw new InvalidAttributeException("Customer not found");
		}
		address.get().setCustomer(customer.get());

		validation.validateUpdateAddress(address.get());
		this.addressRepository.save(address.get());
	}

	@Override
	public void delete(Long id) {
		Optional<AddressEntity> address = this.addressRepository.findById(id);
		if (!address.isPresent()) {
			throw new NotFoundAttributeException("Address not found");
		}

		this.addressRepository.deleteById(id);
	}

}
