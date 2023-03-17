package com.compass.msaudit.service.impl;

import com.compass.msaudit.domain.dto.AuditDto;
import com.compass.msaudit.repository.AuditRepository;
import com.compass.msaudit.service.AuditService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuditServiceImpl implements AuditService {

	@Autowired
	private AuditRepository auditRepository;

	@Autowired
	private ModelMapper mapper;

	@Override
	public List<AuditDto> listByOrderId(String orderId) {
		List<AuditDto> audits = this.auditRepository.findByOrderId(orderId).stream().map(a -> mapper.map(a, AuditDto.class))
				.collect(Collectors.toList());
		return audits;
	}

}
