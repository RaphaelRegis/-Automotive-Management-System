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

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    // CREATE
    @PostMapping()
    public ResponseEntity<Produto> saveProduto(@RequestBody ProdutoDTO produtoDTO) {
        return ResponseEntity.ok(produtoService.saveProduto(produtoDTO));
    }

    // READ
    @GetMapping()
    public ResponseEntity<List<Produto>> findAllProdutos() {
        return ResponseEntity.ok(produtoService.findAllProdutos());
    }

    @GetMapping("/{nome}")
    public ResponseEntity<Produto> findProdutoByNome(@PathVariable("nome") String nome) {
        return ResponseEntity.ok(produtoService.findByNome(nome));
    }

    // UPDATE
    @PatchMapping("/{nome}")
    public ResponseEntity<Produto> updateProdutoByNome(@PathVariable("nome") String nome, @RequestBody ProdutoDTO produtoDTO) {
        return ResponseEntity.ok(produtoService.updateByNome(nome, produtoDTO));
    }

    // DELETE
    @DeleteMapping("/{nome}")
    public ResponseEntity<Produto> deleteProdutoByNome(@PathVariable("nome") String nome) {
        return ResponseEntity.ok(produtoService.deleteByNome(nome));
    }

}
