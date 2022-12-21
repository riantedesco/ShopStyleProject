package com.compass.mscustomer.security;

import com.compass.mscustomer.domain.CustomerEntity;
import com.compass.mscustomer.repository.CustomerRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationByTokenFilter extends OncePerRequestFilter {
	
	private TokenService tokenService;
	private CustomerRepository customerRepository;
	
	public AuthenticationByTokenFilter(TokenService tokenService, CustomerRepository customerRepository) {
		this.tokenService = tokenService;
		this.customerRepository = customerRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		String token = catchToken(request);
		boolean validToken = tokenService.isValidToken(token);
		if (validToken) {
			authenticateCustomer(token);
		}
		filterChain.doFilter(request, response);
	}

	private void authenticateCustomer(String token) {
		Long customerId = tokenService.getCustomerId(token);
		CustomerEntity customer = customerRepository.findById(customerId).get();
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(customer, null, customer.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	private String catchToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;
		}
		return token.substring(7, token.length());
	}

}
