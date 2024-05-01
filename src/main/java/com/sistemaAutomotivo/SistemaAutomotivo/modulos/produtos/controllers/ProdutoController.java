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

    @GetMapping("/{id}")
    public ResponseEntity<Produto> findProdutoById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(produtoService.findById(id));
    }

    // UPDATE
    @PatchMapping("/{id}")
    public ResponseEntity<Produto> updateProdutoById(@PathVariable("id") Integer id, @RequestBody ProdutoDTO produtoDTO) {
        return ResponseEntity.ok(produtoService.updateById(id, produtoDTO));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Produto> deleteProdutoById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(produtoService.deleteById(id));
    }

}
