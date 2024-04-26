package com.sistemaAutomotivo.SistemaAutomotivo.modulos.orcamentos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sistemaAutomotivo.SistemaAutomotivo.modulos.orcamentos.entities.Orcamento;

@Repository
public interface OrcamentoRepository extends JpaRepository<Orcamento, Integer> {
    
}
