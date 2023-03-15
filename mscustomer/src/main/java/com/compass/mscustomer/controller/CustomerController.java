package com.compass.mscustomer.controller;

import com.compass.mscustomer.domain.dto.CustomerDto;
import com.compass.mscustomer.domain.dto.form.CustomerFormDto;
import com.compass.mscustomer.domain.dto.form.CustomerPasswordUpdateFormDto;
import com.compass.mscustomer.domain.dto.form.CustomerUpdateFormDto;
import com.compass.mscustomer.service.CustomerService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/customers")
@Api(value = "Operações de cliente")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@ApiOperation(value = "Cadastra um cliente")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Retorna o cliente recém cadastrado"),
			@ApiResponse(code = 400, message = "Erro na validação dos dados enviados no corpo da requisição")})
	@PostMapping(consumes = "application/json", produces = "application/json")
	@Transactional
	public ResponseEntity<?> save(@RequestBody @Valid CustomerFormDto body) {
		this.customerService.save(body);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@ApiOperation(value = "Busca um cliente pelo id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna o cliente encontrado"),
			@ApiResponse(code = 404, message = "Cliente não encontrado")})
	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<CustomerDto> find(@ApiParam(value = "Id do cliente", required = true, example = "1")
												@PathVariable Long id) {
		return ResponseEntity.ok(this.customerService.find(id));
	}

	@ApiOperation(value = "Atualiza um cliente pelo id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna o cliente atualizado"),
			@ApiResponse(code = 400, message = "Erro na validação dos dados enviados no corpo da requisição"),
			@ApiResponse(code = 404, message = "Cliente não encontrado")})
	@PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
	@Transactional
	public ResponseEntity<?> update(@ApiParam(value = "Id do cliente", required = true, example = "1")
										@PathVariable Long id, @RequestBody @Valid CustomerUpdateFormDto body) {
		this.customerService.update(id, body);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@ApiOperation(value = "Atualiza a senha de um cliente pelo id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna o cliente atualizado"),
			@ApiResponse(code = 400, message = "Erro na validação dos dados enviados no corpo da requisição"),
			@ApiResponse(code = 404, message = "Cliente não encontrado")})
	@PutMapping(value = "/{id}/password", consumes = "application/json", produces = "application/json")
	@Transactional
	public ResponseEntity<?> updatePassword(@ApiParam(value = "Id do cliente", required = true, example = "1")
												@PathVariable Long id,
												@RequestBody @Valid CustomerPasswordUpdateFormDto body) {
		this.customerService.updatePassword(id, body);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

}
