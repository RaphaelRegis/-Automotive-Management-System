package com.sistemaAutomotivo.SistemaAutomotivo.modulos.produtos.services;

import java.util.List;


import com.sistemaAutomotivo.SistemaAutomotivo.modulos.produtos.dto.ProdutoDTO;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.produtos.entities.Produto;

public interface ProdutoService {
    
    // CREATE
    Produto saveProduto(ProdutoDTO produtoDTO);
    
    // READ
    List<Produto> findAllProdutos();
    Produto findById(Integer id);
    
    // UPDATE
    Produto updateById(Integer id, ProdutoDTO produtoDTO);
    
    // DELETE
    Produto deleteById(Integer id);
}
