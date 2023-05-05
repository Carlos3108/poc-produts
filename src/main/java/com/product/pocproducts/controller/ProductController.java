package com.product.pocproducts.controller;

import com.azdevelopment.webproject.dto.UserDTO;
import com.product.pocproducts.dto.ProductDTO;
import com.product.pocproducts.service.IProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@AllArgsConstructor
public class ProductController {

    private IProductService service;

    @Operation(summary = "Listagem de Produtos",
            description = "Listagem de Produtos",
            tags = {"Listagem de Produtos"})
    @ApiResponse(responseCode = "200", description = "Listagem de Produtos")
    @ApiResponses(value = {
            @ApiResponse(content = {
                    @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ProductDTO.class))
                    )})})
    @GetMapping("/all")
    public List<ProductDTO> getAll() {
        return service.getAll();
    }

    @Operation(summary = "Pesquisa de Produto por ID",
            description = "Pesquisa de Produto por ID",
            tags = {"Pesquisa de Produto por ID"})
    @ApiResponse(responseCode = "200", description = "Pesquisa de Produto por ID")
    @ApiResponses(value = {
            @ApiResponse(content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductDTO.class)
                    )})})
    @GetMapping("/{id}")
    public ProductDTO findByID(@PathVariable String id) {
        return service.findByID(id);
    }

    @Operation(summary = "Criação de Produto",
            description = "Criação de Usuário",
            tags = {"Criar"})
    @ApiResponse(responseCode = "201", description = "Criação de Produto")
    @ApiResponses(value = {
            @ApiResponse(content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductDTO.class)
                    )})})
    @PostMapping("/create")
    public ResponseEntity<ProductDTO> create(@RequestBody ProductDTO product){
        return service.create(product);
    }

    @Operation(summary = "Excluir Produto por ID",
            description = "Excluir Produto por ID",
            tags = {"Apagar"})
    @ApiResponse(responseCode = "200", description = "Excluir Produto por ID")
    @ApiResponses(value = {
            @ApiResponse(content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductDTO.class)
                    )})})
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") String id)  {
        service.delete(id);
    }

    @Operation(summary = "Atualizar Produto",
            description = "Atualizar Produto",
            tags = {"Atualizar"})
    @ApiResponse(responseCode = "200", description = "Atualizar Produto")
    @ApiResponses(value = {
            @ApiResponse(content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductDTO.class)
                    )})})
    @PutMapping("/update")
    public ResponseEntity<ProductDTO> update(@RequestBody ProductDTO product){
        return ResponseEntity.ok(service.update(product));
    }
}