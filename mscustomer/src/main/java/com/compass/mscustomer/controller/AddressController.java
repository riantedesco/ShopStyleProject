package com.compass.mscustomer.controller;

import com.compass.mscustomer.domain.dto.form.AddressFormDto;
import com.compass.mscustomer.domain.dto.form.AddressUpdateFormDto;
import com.compass.mscustomer.service.AddressService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/addresses")
@Api(value = "Operações de endereço")
public class AddressController {

	@Autowired
	private AddressService addressService;

	@ApiOperation(value = "Cadastra um endereço")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Retorna o endereço  recém cadastrado"),
			@ApiResponse(code = 400, message = "Erro na validação dos dados enviados no corpo da requisição")})
	@PostMapping(consumes = "application/json", produces = "application/json")
	@Transactional
	public ResponseEntity<?> save(@RequestBody @Valid AddressFormDto body) {
		this.addressService.save(body);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@ApiOperation(value = "Atualiza um endereço pelo id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna o endereço atualizado"),
			@ApiResponse(code = 400, message = "Erro na validação dos dados enviados no corpo da requisição"),
			@ApiResponse(code = 404, message = "Endereço não encontrado")})
	@PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
	@Transactional
	public ResponseEntity<?> update(@ApiParam(value = "Id do endereço", required = true, example = "1")
										@PathVariable Long id, @RequestBody @Valid AddressUpdateFormDto body) {
		this.addressService.update(id, body);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@ApiOperation(value = "Remove um endereço pelo id")
	@ApiResponses(value = {
			@ApiResponse(code = 204, message = "Endereço deletado"),
			@ApiResponse(code = 404, message = "Endereço não encontrado")})
	@DeleteMapping(value = "/{id}")
	@Transactional
	public ResponseEntity<?> delete(@ApiParam(value = "Id do endereço", required = true, example = "1")
										@PathVariable Long id) {
		this.addressService.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
