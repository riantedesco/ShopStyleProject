package com.compass.msorder.controller;

import com.compass.msorder.domain.dto.OrderDto;
import com.compass.msorder.domain.dto.form.OrderFormDto;
import com.compass.msorder.service.OrderService;
import com.compass.msorder.utils.constants.StatusOrderOption;
import com.mongodb.lang.Nullable;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/v1/orders")
@Api(value = "Operações de pedido")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@ApiOperation(value = "Cadastra um pedido")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Retorna o pedido recém cadastrado"),
			@ApiResponse(code = 400, message = "Erro na validação dos dados enviados no corpo da requisição")})
	@PostMapping(consumes = "application/json", produces = "application/json")
	@Transactional
	public ResponseEntity<?> save(@RequestBody @Valid OrderFormDto body) {
		this.orderService.save(body);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@ApiOperation(value = "Lista os pedidos")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna os pedidos cadastrados")})
	@GetMapping(produces = "application/json")
	public ResponseEntity<List<OrderDto>> list(
			@ApiParam(value = "Data de início dos pedidos", required = true, example = "2000-01-01T00:00")
				@RequestParam String startDate,
			@ApiParam(value = "Data de fim dos pedidos", example = "2000-01-31T00:00")
				@RequestParam(required = false) String endDate,
			@ApiParam(value = "Status dos pedidos", example = "PAYMENT_SUCCESSFUL",
					allowableValues = "PROCESSING_PAYMENT, PAYMENT_SUCCESSFUL, PAYMENT_NOT_FOUND, PAYMENT_INACTIVE" +
							"PAYMENT_NOT_INSTALLMENT, PAYMENT_AMOUNT_NOT_AVAILABLE")
				@RequestParam(required = false) StatusOrderOption status) {
		return ResponseEntity.ok(this.orderService.list(startDate, endDate, status));
	}

	@ApiOperation(value = "Lista os pedidos pelo cliente")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna os pedidos cadastrados")})
	@GetMapping(value = "/customers/{customerId}", produces = "application/json")
	public ResponseEntity<List<OrderDto>> list(@ApiParam(value = "Id do cliente", required = true, example = "1") @PathVariable Long customerId) {
		return ResponseEntity.ok(this.orderService.listByCustomer(customerId));
	}
}
