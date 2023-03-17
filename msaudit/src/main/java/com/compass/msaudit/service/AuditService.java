package com.compass.msaudit.service;

import com.compass.msaudit.domain.dto.AuditDto;

import java.util.List;

public interface AuditService {

    List<AuditDto> listByOrderId(String orderId);

}
