package com.sistemaAutomotivo.SistemaAutomotivo.modulos.produtos.services;

import java.util.List;


import com.sistemaAutomotivo.SistemaAutomotivo.modulos.produtos.dto.ProdutoDTO;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.produtos.entities.Produto;

public interface ProdutoService {
    
    Produto saveProduto(ProdutoDTO produtoDTO);

    List<Produto> findAllProdutos();

    Produto findById(Integer id);

    Produto updateById(Integer id, ProdutoDTO produtoDTO);

    Produto deleteById(Integer id);
}
