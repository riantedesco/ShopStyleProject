package com.compass.msorder.controller;

import com.compass.msorder.domain.dto.form.SkuFormDto;
import com.compass.msorder.domain.dto.form.SkuUpdateFormDto;
import com.compass.msorder.service.SkuService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/skus")
@Api(value = "Operações de sku")
public class SkuController {

	@Autowired
	private SkuService skuService;

	@ApiOperation(value = "Cadastra uma sku")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Retorna a sku recém cadastrada"),
			@ApiResponse(code = 400, message = "Erro na validação dos dados enviados no corpo da requisição")})
	@PostMapping(consumes = "application/json", produces = "application/json")
	@Transactional
	public ResponseEntity<?> save(@RequestBody @Valid SkuFormDto body) {
		this.skuService.save(body);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@ApiOperation(value = "Atualiza uma sku pelo id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna a sku atualizada"),
			@ApiResponse(code = 400, message = "Erro na validação dos dados enviados no corpo da requisição"),
			@ApiResponse(code = 404, message = "Sku não encontrada")})
	@PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
	@Transactional
	public ResponseEntity<?> update(@ApiParam(value = "Id da sku", required = true, example = "1") @PathVariable Long id, @RequestBody @Valid SkuUpdateFormDto body) {
		this.skuService.update(id, body);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@ApiOperation(value = "Remove uma sku pelo id")
	@ApiResponses(value = {
			@ApiResponse(code = 204, message = "Sku deletada"),
			@ApiResponse(code = 404, message = "Sku não encontrada")})
	@DeleteMapping(value = "/{id}")
	@Transactional
	public ResponseEntity<?> delete(@ApiParam(value = "Id da sku", required = true, example = "1") @PathVariable Long id) {
		this.skuService.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
