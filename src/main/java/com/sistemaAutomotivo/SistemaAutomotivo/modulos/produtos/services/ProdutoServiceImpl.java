package com.sistemaAutomotivo.SistemaAutomotivo.modulos.produtos.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemaAutomotivo.SistemaAutomotivo.modulos.produtos.dto.ProdutoDTO;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.produtos.entities.Produto;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.produtos.repositories.ProdutoRepository;

@Service
public class ProdutoServiceImpl implements ProdutoService {
    
    @Autowired
    private ProdutoRepository produtoRepository;
    
    @Override
    public Produto saveProduto(ProdutoDTO produtoDTO) {
        // colocar if existsByCPF
        return produtoRepository.save(DTOtoProduto(produtoDTO));
    }

    @Override
    public List<Produto> findAllProdutos() {
       // colocar if isEmpty
        return produtoRepository.findAll();
    }

    @Override
    public Produto findById(Integer id) {
        return produtoRepository.findById(id).get();
    }

    @Override
    public Produto updateById(Integer id, ProdutoDTO produtoDTO) {
        // pega o produto existente com base no id
        Produto produtoExistente = findById(id);
        
        // constroi o produto atualizado
        Produto produtoAtualizado = DTOtoProduto(produtoDTO);
        produtoAtualizado.setId_produto(produtoExistente.getId_produto());

        // copia as propriedades do produto atualizado para o existente
        BeanUtils.copyProperties(produtoAtualizado, produtoExistente);

        //salva e retorna novamente o produto existente
        return produtoRepository.save(produtoExistente);
    }

    @Override
    public Produto deleteById(Integer id) {
        Produto produtoExcluido = produtoRepository.findById(id).get();

        produtoRepository.delete(produtoExcluido);

        return produtoExcluido;
    }

    // metodos auxiliares
    Produto DTOtoProduto(ProdutoDTO produtoDTO) {
        Produto produto = new Produto(
            null,
            produtoDTO.nome(),
            produtoDTO.categoria(),
            produtoDTO.valor());

        return produto;
    }




}