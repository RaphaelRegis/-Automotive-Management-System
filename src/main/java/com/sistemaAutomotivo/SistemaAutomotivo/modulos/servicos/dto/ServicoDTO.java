package com.sistemaAutomotivo.SistemaAutomotivo.modulos.servicos.dto;

import com.sistemaAutomotivo.SistemaAutomotivo.modulos.servicos.entities.enums.TipoServico;

public record ServicoDTO(
    String nome,
    double valor,
    TipoServico tipoServico
    ){}
