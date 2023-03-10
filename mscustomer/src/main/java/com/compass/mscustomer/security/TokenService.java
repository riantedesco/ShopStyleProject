package com.compass.mscustomer.security;

import com.compass.mscustomer.domain.CustomerEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {
	
	@Value("${mscustomer.jwt.expiration}")
	private String expiration;
	
	@Value("${mscustomer.jwt.secret}")
	private String secret;

	public String generateToken(Authentication authentication) {
		CustomerEntity loggedCustomer = (CustomerEntity) authentication.getPrincipal();
		Date today = new Date();
		Date expirationDate = new Date(today.getTime() + Long.parseLong(expiration));
		return Jwts.builder()
				.setIssuer("API Projeto Mercado")
				.setSubject(loggedCustomer.getId().toString())
				.setIssuedAt(today)
				.setExpiration(expirationDate)
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
	}

	public boolean isValidToken(String token) {
		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Long getCustomerId(String token) {
		Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
		return Long.parseLong(claims.getSubject());
	}

}
