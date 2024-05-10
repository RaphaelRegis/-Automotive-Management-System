package com.sistemaAutomotivo.SistemaAutomotivo.modulos.produtos.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sistemaAutomotivo.SistemaAutomotivo.modulos.produtos.entities.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    boolean existsByNome(String nome);

    Optional<Produto> findByNome(String nome);
    
}
