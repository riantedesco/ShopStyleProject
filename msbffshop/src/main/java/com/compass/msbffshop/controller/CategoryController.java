package com.compass.msbffshop.controller;

import com.compass.msbffshop.domain.dto.CategoryDto;
import com.compass.msbffshop.domain.dto.CategoryWithProductsDto;
import com.compass.msbffshop.domain.dto.form.CategoryFormDto;
import com.compass.msbffshop.service.CategoryService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/categories")
@Api(value = "Operações de categoria")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@ApiOperation(value = "Cadastra uma categoria")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Retorna a categoria recém cadastrada"),
			@ApiResponse(code = 400, message = "Erro na validação dos dados enviados no corpo da requisição")})
	@PostMapping(consumes = "application/json", produces = "application/json")
	@Transactional
	public ResponseEntity<?> save(@RequestBody @Valid CategoryFormDto body) {
		this.categoryService.save(body);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@ApiOperation(value = "Lista as categorias")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna as categorias cadastradas")})
	@GetMapping(produces = "application/json")
	public ResponseEntity<List<CategoryDto>> list() {
		return ResponseEntity.ok(this.categoryService.list());
	}

	@ApiOperation(value = "Busca uma categoria pelo id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna a categoria encontrada"),
			@ApiResponse(code = 404, message = "Categoria não encontrada")})
	@GetMapping(value = "/{id}/products", produces = "application/json")
	public ResponseEntity<CategoryWithProductsDto> find(@ApiParam(value = "Id da categoria", required = true, example = "1") @PathVariable Long id) {
		return ResponseEntity.ok(this.categoryService.find(id));
	}

	@ApiOperation(value = "Atualiza uma categoria pelo id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna a categoria atualizada"),
			@ApiResponse(code = 400, message = "Erro na validação dos dados enviados no corpo da requisição"),
			@ApiResponse(code = 404, message = "Categoria não encontrada")})
	@PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
	@Transactional
	public ResponseEntity<?> update(@ApiParam(value = "Id da categoria", required = true, example = "1") @PathVariable Long id, @RequestBody @Valid CategoryFormDto body) {
		this.categoryService.update(id, body);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@ApiOperation(value = "Remove uma categoria pelo id")
	@ApiResponses(value = {
			@ApiResponse(code = 204, message = "Categoria deletada"),
			@ApiResponse(code = 404, message = "Categoria não encontrada")})
	@DeleteMapping(value = "/{id}")
	@Transactional
	public ResponseEntity<?> delete(@ApiParam(value = "Id da categoria", required = true, example = "1") @PathVariable Long id) {
		this.categoryService.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
