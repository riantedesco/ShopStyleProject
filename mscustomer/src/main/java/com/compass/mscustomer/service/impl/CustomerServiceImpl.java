package com.compass.mscustomer.service.impl;

import com.compass.mscustomer.domain.CustomerEntity;
import com.compass.mscustomer.domain.dto.AddressDto;
import com.compass.mscustomer.domain.dto.CustomerDto;
import com.compass.mscustomer.domain.dto.CustomerUpdateDto;
import com.compass.mscustomer.domain.dto.form.CustomerFormDto;
import com.compass.mscustomer.domain.dto.form.CustomerPasswordUpdateFormDto;
import com.compass.mscustomer.domain.dto.form.CustomerUpdateFormDto;
import com.compass.mscustomer.exception.InvalidAttributeException;
import com.compass.mscustomer.exception.NotFoundAttributeException;
import com.compass.mscustomer.repository.AddressRepository;
import com.compass.mscustomer.repository.CustomerRepository;
import com.compass.mscustomer.service.CustomerService;
import com.compass.mscustomer.util.validation.Validation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private Validation validation;

	@Override
	public void save(CustomerFormDto body) {
		mapper.getConfiguration().setAmbiguityIgnored(true);
		CustomerEntity customer = mapper.map(body, CustomerEntity.class);
		validation.validateSaveCustomer(customer);
		customer.setPassword(new BCryptPasswordEncoder().encode(body.getPassword()));
		this.customerRepository.save(customer);
	}

	@Override
	public CustomerDto find(Long id) {
		Optional<CustomerEntity> customer = this.customerRepository.findById(id);
		if (!customer.isPresent()) {
			throw new NotFoundAttributeException("Customer not found");
		} else {
			CustomerDto customerDtoResponse = mapper.map(customer.get(), CustomerDto.class);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			String formattedDate = customer.get().getBirthdate().format(formatter);
			customerDtoResponse.setBirthdate(formattedDate);
			List<AddressDto> addresses = this.addressRepository.findByCustomerId(id).stream().map(a -> mapper.map(a, AddressDto.class))
					.collect(Collectors.toList());
			if (!addresses.isEmpty()) {
				customerDtoResponse.setAddresses(addresses);
			}
			return customerDtoResponse;
		}
	}

	@Override
	public void update(Long id, CustomerUpdateFormDto body) {
		Optional<CustomerEntity> customer = this.customerRepository.findById(id);
		if (!customer.isPresent()) {
			throw new NotFoundAttributeException("Customer not found");
		}
		customer.get().setCpf(body.getCpf());
		customer.get().setFirstName(body.getFirstName());
		customer.get().setLastName(body.getLastName());
		customer.get().setSex(body.getSex());
		customer.get().setBirthdate(body.getBirthdate());
		customer.get().setEmail(body.getEmail());
		customer.get().setActive(body.getActive());
		validation.validateUpdateCustomer(customer.get());
		this.customerRepository.save(customer.get());
	}

	@Override
	public void updatePassword(Long id, CustomerPasswordUpdateFormDto body) {
		Optional<CustomerEntity> customer = this.customerRepository.findById(id);
		if (!customer.isPresent()) {
			throw new NotFoundAttributeException("Customer not found");
		} else {
			if (!body.getNewPassword().equals(body.getConfirmNewPassword())) {
				throw new InvalidAttributeException("Passwords don't match");
			}
			validation.validatePasswordUpdateCustomer(customer.get());
			customer.get().setPassword(new BCryptPasswordEncoder().encode(body.getNewPassword()));
		}
		this.customerRepository.save(customer.get());
	}

}
