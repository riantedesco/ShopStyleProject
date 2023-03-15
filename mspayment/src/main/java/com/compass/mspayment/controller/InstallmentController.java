package com.compass.mspayment.controller;

import com.compass.mspayment.domain.dto.form.InstallmentFormDto;
import com.compass.mspayment.domain.dto.form.InstallmentUpdateFormDto;
import com.compass.mspayment.service.InstallmentService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/installments")
@Api(value = "Operações de parcelas")
public class InstallmentController {

	@Autowired
	private InstallmentService installmentService;

	@ApiOperation(value = "Cadastra uma parcela")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Retorna a parcela recém cadastrada"),
			@ApiResponse(code = 400, message = "Erro na validação dos dados enviados no corpo da requisição")})
	@PostMapping(consumes = "application/json", produces = "application/json")
	@Transactional
	public ResponseEntity<?> save(@RequestBody @Valid InstallmentFormDto body) {
		this.installmentService.save(body);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@ApiOperation(value = "Atualiza uma parcela pelo id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna a parcela atualizada"),
			@ApiResponse(code = 400, message = "Erro na validação dos dados enviados no corpo da requisição"),
			@ApiResponse(code = 404, message = "Parcela não encontrada")})
	@PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
	@Transactional
	public ResponseEntity<?> update(@ApiParam(value = "Id da parcela", required = true, example = "1") @PathVariable Long id, @RequestBody @Valid InstallmentUpdateFormDto body) {
		this.installmentService.update(id, body);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@ApiOperation(value = "Remove uma parcela pelo id")
	@ApiResponses(value = {
			@ApiResponse(code = 204, message = "Parcela deletada"),
			@ApiResponse(code = 404, message = "Parcela não encontrada")})
	@DeleteMapping(value = "/{id}")
	@Transactional
	public ResponseEntity<?> delete(@ApiParam(value = "Id da parcela", required = true, example = "1") @PathVariable Long id) {
		this.installmentService.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
