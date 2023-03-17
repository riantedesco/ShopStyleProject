package com.compass.mscustomer.service.impl;

import com.compass.mscustomer.domain.CustomerEntity;
import com.compass.mscustomer.domain.dto.AddressDto;
import com.compass.mscustomer.domain.dto.CustomerDto;
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
	private Validation validation;

	@Autowired
	private ModelMapper mapper;

	@Override
	public void save(CustomerFormDto body) {
		mapper.getConfiguration().setAmbiguityIgnored(true);
		CustomerEntity customer = mapper.map(body, CustomerEntity.class);

		Optional<CustomerEntity> customerByEmail = this.customerRepository.findByEmail(body.getEmail());
		if (customerByEmail.isPresent()) {
			throw new InvalidAttributeException("Email " + body.getEmail() + " already exists");
		}

		customer.setPassword(new BCryptPasswordEncoder().encode(body.getPassword()));
		validation.validateCustomer(customer);
		this.customerRepository.save(customer);
	}

	@Override
	public CustomerDto find(Long id) {
		Optional<CustomerEntity> customer = this.customerRepository.findById(id);
		if (!customer.isPresent()) {
			throw new NotFoundAttributeException("Customer " + id + " not found");
		}

		CustomerDto customerDtoResponse = mapper.map(customer.get(), CustomerDto.class);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String formattedDate = customer.get().getBirthdate().format(formatter);
		customerDtoResponse.setBirthdate(formattedDate);

		List<AddressDto> addresses = this.addressRepository.findByCustomerId(id)
				.stream().map(a -> mapper.map(a, AddressDto.class))
				.collect(Collectors.toList());
		if (!addresses.isEmpty()) {
			customerDtoResponse.setAddresses(addresses);
		}
		return customerDtoResponse;

	}

	@Override
	public void update(Long id, CustomerUpdateFormDto body) {
		Optional<CustomerEntity> customer = this.customerRepository.findById(id);
		if (!customer.isPresent()) {
			throw new NotFoundAttributeException("Customer " + id + " not found");
		}

		customer.get().setCpf(body.getCpf());
		customer.get().setFirstName(body.getFirstName());
		customer.get().setLastName(body.getLastName());
		customer.get().setSex(body.getSex());
		customer.get().setBirthdate(body.getBirthdate());
		customer.get().setEmail(body.getEmail());
		customer.get().setActive(body.getActive());

		this.customerRepository.save(customer.get());
	}

	@Override
	public void updatePassword(Long id, CustomerPasswordUpdateFormDto body) {
		Optional<CustomerEntity> customer = this.customerRepository.findById(id);
		if (!customer.isPresent()) {
			throw new NotFoundAttributeException("Customer " + id + " not found");
		}
		if (!body.getNewPassword().equals(body.getConfirmNewPassword())) {
			throw new InvalidAttributeException("Passwords don't match");
		}
		customer.get().setPassword(new BCryptPasswordEncoder().encode(body.getNewPassword()));
		this.customerRepository.save(customer.get());
	}

}
