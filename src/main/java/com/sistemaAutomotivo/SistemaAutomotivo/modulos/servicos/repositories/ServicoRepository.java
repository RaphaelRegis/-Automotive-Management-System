package com.sistemaAutomotivo.SistemaAutomotivo.modulos.servicos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.sistemaAutomotivo.SistemaAutomotivo.modulos.servicos.entities.Servico;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Integer> {
    
}
