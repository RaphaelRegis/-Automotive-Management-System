package com.sistemaAutomotivo.SistemaAutomotivo.modulos.orcamentos.services;

import java.util.List;

import com.sistemaAutomotivo.SistemaAutomotivo.modulos.orcamentos.dto.OrcamentoDTO;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.orcamentos.entities.Orcamento;

public interface OrcamentoService {
    
    // CREATE
    Orcamento saveOrcamento(OrcamentoDTO orcamentoDTO);

    // READ
    List<Orcamento> findAllOrcamentos();
    Orcamento findById(Integer id);

    // UPDATE
    Orcamento updateById(Integer id, OrcamentoDTO orcamentoDTO);

    // DELETE
    Orcamento deleteById(Integer id);
}
