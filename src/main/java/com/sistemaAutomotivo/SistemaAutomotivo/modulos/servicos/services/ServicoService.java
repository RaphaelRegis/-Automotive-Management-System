package com.sistemaAutomotivo.SistemaAutomotivo.modulos.servicos.services;

import java.util.List;


import com.sistemaAutomotivo.SistemaAutomotivo.modulos.servicos.dto.ServicoDTO;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.servicos.entities.Servico;

public interface ServicoService {
    
    // CREATE
    Servico saveServico(ServicoDTO servicoDTO);
    
    // READ
    List<Servico> findAllServicos();
    Servico findByNome(String nome);
    
    // UPDATE
    Servico updateByNome(String nome, ServicoDTO servicoDTO);
    
    // DELETE
    Servico deleteByNome(String nome);
}
