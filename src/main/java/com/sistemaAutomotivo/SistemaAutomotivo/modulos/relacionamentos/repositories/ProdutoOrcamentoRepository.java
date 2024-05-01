package com.sistemaAutomotivo.SistemaAutomotivo.modulos.relacionamentos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sistemaAutomotivo.SistemaAutomotivo.modulos.relacionamentos.entities.ProdutoOrcamento;

@Repository
public interface ProdutoOrcamentoRepository extends JpaRepository<ProdutoOrcamento, Integer> {
    
}
