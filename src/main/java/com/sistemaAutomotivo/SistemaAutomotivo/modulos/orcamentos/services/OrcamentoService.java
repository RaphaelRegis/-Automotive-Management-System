package com.sistemaAutomotivo.SistemaAutomotivo.modulos.orcamentos.services;

import java.util.List;


import com.sistemaAutomotivo.SistemaAutomotivo.modulos.orcamentos.dto.OrcamentoDTO;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.orcamentos.entities.Orcamento;

public interface OrcamentoService {
    
    Orcamento saveOrcamento(OrcamentoDTO orcamentoDTO);

    List<Orcamento> findAllOrcamentos();

    Orcamento findById(Integer id);

    Orcamento updateById(Integer id, OrcamentoDTO orcamentoDTO);

    Orcamento deleteById(Integer id);
}
