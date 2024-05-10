package com.sistemaAutomotivo.SistemaAutomotivo.modulos.produtos.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemaAutomotivo.SistemaAutomotivo.modulos.orcamentos.entities.enums.Status;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.produtos.dto.ProdutoDTO;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.produtos.entities.Produto;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.produtos.repositories.ProdutoRepository;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.relacionamentos.entities.ProdutoOrcamento;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    // CREATE
    @Override
    public Produto saveProduto(ProdutoDTO produtoDTO) {

        if (produtoRepository.existsByNome(produtoDTO.nome())) {
            throw new RuntimeException("Nome de produto já cadastrado!");
        }

        return produtoRepository.save(DTOtoProduto(produtoDTO));
    }

    // READ
    @Override
    public List<Produto> findAllProdutos() {

        List<Produto> todosProdutos = produtoRepository.findAll();

        if (todosProdutos.isEmpty()) {
            throw new RuntimeException("Nenhum produto encontrado!");
        }

        return todosProdutos;
    }

    @Override
    public Produto findByNome(String nome) {

        nome = nome.replace("_", " ");

        return produtoRepository.findByNome(nome)
                .orElseThrow(() -> new RuntimeException("Nenhum produto com esse nome encontrado!"));
    }

    // UPDATE
    @Override
    public Produto updateByNome(String nome, ProdutoDTO produtoDTO) {
        // pega o produto existente com base no nome
        Produto produtoExistente = findByNome(nome);

        // constroi o produto atualizado
        produtoExistente.setNome(produtoDTO.nome());
        produtoExistente.setCategoria(produtoDTO.categoria());

        // atualiza o valor do produto
        if (produtoDTO.valor() != produtoExistente.getValor()) {
            produtoExistente.setValor(produtoDTO.valor());

            // atualiza o valor dos produto_orcamento apenas se o Orcamento associado ainda estiver com Status ABERTO
            for (ProdutoOrcamento produtoOrcamento : produtoExistente.getProdutoOrcamentos()) {
                if (produtoOrcamento.getOrcamento().getStatus().equals(Status.ABERTO)) {
                    produtoOrcamento.setValor(produtoExistente.getValor() * produtoOrcamento.getQuantidade());
                }
            }
        }

        // salva e retorna novamente o produto existente
        return produtoRepository.save(produtoExistente);
    }

    // DELETE
    @Override
    public Produto deleteByNome(String nome) {
        Produto produtoExcluido = findByNome(nome);

        produtoRepository.delete(produtoExcluido);

        return produtoExcluido;
    }

    // metodos auxiliares
    Produto DTOtoProduto(ProdutoDTO produtoDTO) {
        Produto produto = new Produto(
                null,
                produtoDTO.nome(),
                produtoDTO.categoria(),
                produtoDTO.valor(),
                new ArrayList<>());

        return produto;
    }

}
