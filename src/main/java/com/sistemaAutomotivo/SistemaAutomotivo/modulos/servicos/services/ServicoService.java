package com.sistemaAutomotivo.SistemaAutomotivo.modulos.servicos.services;

import java.util.List;


import com.sistemaAutomotivo.SistemaAutomotivo.modulos.servicos.dto.ServicoDTO;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.servicos.entities.Servico;

public interface ServicoService {
    
    // CREATE
    Servico saveServico(ServicoDTO servicoDTO);
    
    // READ
    List<Servico> findAllServicos();
    Servico findById(Integer id);
    
    // UPDATE
    Servico updateById(Integer id, ServicoDTO servicoDTO);
    
    // DELETE
    Servico deleteById(Integer id);
}
