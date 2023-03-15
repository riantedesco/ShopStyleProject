package com.compass.mspayment.controller;

import com.compass.mspayment.domain.dto.PaymentDto;
import com.compass.mspayment.domain.dto.form.PaymentFormDto;
import com.compass.mspayment.domain.dto.form.PaymentUpdateFormDto;
import com.compass.mspayment.service.PaymentService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/payments")
@Api(value = "Operações de pagamentos")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;

	@ApiOperation(value = "Cadastra um pagamento")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Retorna o pagamento recém cadastrado"),
			@ApiResponse(code = 400, message = "Erro na validação dos dados enviados no corpo da requisição")})
	@PostMapping(consumes = "application/json", produces = "application/json")
	@Transactional
	public ResponseEntity<?> save(@RequestBody @Valid PaymentFormDto body) {
		this.paymentService.save(body);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@ApiOperation(value = "Lista os pagamentos")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna os pagamentos cadastrados")})
	@GetMapping(produces = "application/json")
	public ResponseEntity<List<PaymentDto>> list() {
		return ResponseEntity.ok(this.paymentService.list());
	}

	@ApiOperation(value = "Atualiza um pagamento pelo id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna o pagamento atualizado"),
			@ApiResponse(code = 400, message = "Erro na validação dos dados enviados no corpo da requisição"),
			@ApiResponse(code = 404, message = "Pagamento não encontrado")})
	@PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
	@Transactional
	public ResponseEntity<?> update(@ApiParam(value = "Id do pagamento", required = true, example = "1") @PathVariable Long id, @RequestBody @Valid PaymentUpdateFormDto body) {
		this.paymentService.update(id, body);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@ApiOperation(value = "Remove um pagamento pelo id")
	@ApiResponses(value = {
			@ApiResponse(code = 204, message = "Pagamento deletado"),
			@ApiResponse(code = 404, message = "Pagamento não encontrado")})
	@DeleteMapping(value = "/{id}")
	@Transactional
	public ResponseEntity<?> delete(@ApiParam(value = "Id do pagamento", required = true, example = "1") @PathVariable Long id) {
		this.paymentService.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
