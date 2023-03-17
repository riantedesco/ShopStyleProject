package com.compass.msaudit.controller;

import com.compass.msaudit.domain.dto.AuditDto;
import com.compass.msaudit.service.AuditService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/audits")
@Api(value = "Operações de auditoria")
public class AuditController {

	@Autowired
	private AuditService auditService;

	@ApiOperation(value = "Lista as auditorias pelo pedido")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna as auditorias cadastradas")})
	@GetMapping(value = "/orders/{orderId}", produces = "application/json")
	public ResponseEntity<List<AuditDto>> list(
			@ApiParam(value = "Id do pedido", required = true, example = "1") @PathVariable String orderId) {
		return ResponseEntity.ok(this.auditService.listByOrderId(orderId));
	}

}
