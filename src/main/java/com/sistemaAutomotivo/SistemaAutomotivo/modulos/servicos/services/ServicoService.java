package com.sistemaAutomotivo.SistemaAutomotivo.modulos.servicos.services;

import java.util.List;


import com.sistemaAutomotivo.SistemaAutomotivo.modulos.servicos.dto.ServicoDTO;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.servicos.entities.Servico;

public interface ServicoService {
    
    Servico saveServico(ServicoDTO servicoDTO);

    List<Servico> findAllServicos();

    Servico findById(Integer id);

    Servico updateById(Integer id, ServicoDTO servicoDTO);

    Servico deleteById(Integer id);
}
