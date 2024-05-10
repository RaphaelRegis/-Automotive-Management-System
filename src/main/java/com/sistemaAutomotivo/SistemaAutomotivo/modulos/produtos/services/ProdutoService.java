package com.sistemaAutomotivo.SistemaAutomotivo.modulos.produtos.services;

import java.util.List;


import com.sistemaAutomotivo.SistemaAutomotivo.modulos.produtos.dto.ProdutoDTO;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.produtos.entities.Produto;

public interface ProdutoService {
    
    // CREATE
    Produto saveProduto(ProdutoDTO produtoDTO);
    
    // READ
    List<Produto> findAllProdutos();
    Produto findByNome(String nome);
    
    // UPDATE
    Produto updateByNome(String nome, ProdutoDTO produtoDTO);
    
    // DELETE
    Produto deleteByNome(String nome);
}
