package com.sistemaAutomotivo.SistemaAutomotivo.modulos.relacionamentos.dto;

public record ServicoOrcamentoDTO(
    Integer idServico,
    Integer idOrcamento,
    Integer idEquipe,
    String observacao
) {}
