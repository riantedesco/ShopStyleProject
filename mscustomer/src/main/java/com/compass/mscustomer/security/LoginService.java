package com.compass.mscustomer.security;

import com.compass.mscustomer.domain.CustomerEntity;
import com.compass.mscustomer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService implements UserDetailsService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<CustomerEntity> customer = this.customerRepository.findByEmail(email);
		if (!customer.isPresent()) {
			throw new UsernameNotFoundException("Invalid data");
		}
		return customer.get();
	}
}
