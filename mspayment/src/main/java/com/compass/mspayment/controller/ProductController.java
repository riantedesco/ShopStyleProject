package com.compass.mspayment.controller;

import com.compass.mspayment.domain.dto.ProductDto;
import com.compass.mspayment.domain.dto.form.ProductFormDto;
import com.compass.mspayment.domain.dto.form.ProductUpdateFormDto;
import com.compass.mspayment.service.ProductService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/products")
@Api(value = "Operações de produto")
public class ProductController {

	@Autowired
	private ProductService productService;

	@ApiOperation(value = "Cadastra um produto")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Retorna o produto recém cadastrado"),
			@ApiResponse(code = 400, message = "Erro na validação dos dados enviados no corpo da requisição")})
	@PostMapping(consumes = "application/json", produces = "application/json")
	@Transactional
	public ResponseEntity<?> save(@RequestBody @Valid ProductFormDto body) {
		this.productService.save(body);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@ApiOperation(value = "Lista os produtos")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna os produtos cadastrados")})
	@GetMapping(produces = "application/json")
	public ResponseEntity<List<ProductDto>> list() {
		return ResponseEntity.ok(this.productService.list());
	}

	@ApiOperation(value = "Busca um produto pelo id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna o produto encontrado"),
			@ApiResponse(code = 404, message = "Produto não encontrado")})
	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<ProductDto> find(@ApiParam(value = "Id do produto", required = true, example = "1") @PathVariable Long id) {
		return ResponseEntity.ok(this.productService.find(id));
	}

	@ApiOperation(value = "Atualiza um produto pelo id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna o produto atualizado"),
			@ApiResponse(code = 400, message = "Erro na validação dos dados enviados no corpo da requisição"),
			@ApiResponse(code = 404, message = "Produto não encontrado")})
	@PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
	@Transactional
	public ResponseEntity<?> update(@ApiParam(value = "Id do produto", required = true, example = "1") @PathVariable Long id, @RequestBody @Valid ProductUpdateFormDto body) {
		this.productService.update(id, body);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@ApiOperation(value = "Remove um produto pelo id")
	@ApiResponses(value = {
			@ApiResponse(code = 204, message = "Produto deletado"),
			@ApiResponse(code = 404, message = "Produto não encontrado")})
	@DeleteMapping(value = "/{id}")
	@Transactional
	public ResponseEntity<?> delete(@ApiParam(value = "Id do produto", required = true, example = "1") @PathVariable Long id) {
		this.productService.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
