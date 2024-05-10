package com.sistemaAutomotivo.SistemaAutomotivo.modulos.relacionamentos.dto;

public record ProdutoOrcamentoDTO(
    String nomeProduto,
    Integer idOrcamento,
    Integer qtd,
    String observacao
) {}
