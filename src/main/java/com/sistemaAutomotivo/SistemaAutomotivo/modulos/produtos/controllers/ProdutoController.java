package com.sistemaAutomotivo.SistemaAutomotivo.modulos.produtos.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistemaAutomotivo.SistemaAutomotivo.modulos.produtos.dto.ProdutoDTO;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.produtos.entities.Produto;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.produtos.services.ProdutoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/produtos")
@Tag(name = "Módulo Produtos")
@SecurityRequirement(name = "bearerAuth")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    // CREATE
    @PostMapping()
    @Operation(
        summary = "Salvar Produto"
    )
    public ResponseEntity<Produto> saveProduto(@RequestBody ProdutoDTO produtoDTO) {
        return ResponseEntity.ok(produtoService.saveProduto(produtoDTO));
    }

    // READ
    @GetMapping()
    @Operation(
        summary = "Encontrar todos os Produtos"
    )
    public ResponseEntity<List<Produto>> findAllProdutos() {
        return ResponseEntity.ok(produtoService.findAllProdutos());
    }

    @GetMapping("/{nome}")
    @Operation(
        summary = "Encontrar Produto por nome"
    )
    public ResponseEntity<Produto> findProdutoByNome(@PathVariable("nome") String nome) {
        return ResponseEntity.ok(produtoService.findByNome(nome));
    }

    // UPDATE
    @PatchMapping("/{nome}")
    @Operation(
        summary = "Atualizar Produto por nome"
    )
    public ResponseEntity<Produto> updateProdutoByNome(@PathVariable("nome") String nome, @RequestBody ProdutoDTO produtoDTO) {
        return ResponseEntity.ok(produtoService.updateByNome(nome, produtoDTO));
    }

    // DELETE
    @DeleteMapping("/{nome}")
    @Operation(
        summary = "Deletar Produto por nome"
    )
    public ResponseEntity<Produto> deleteProdutoByNome(@PathVariable("nome") String nome) {
        return ResponseEntity.ok(produtoService.deleteByNome(nome));
    }

}
