package com.sistemaAutomotivo.SistemaAutomotivo.modulos.relacionamentos.dto;

public record ProdutoOrcamentoDTO(
    Integer idProduto,
    Integer idOrcamento,
    Integer qtd,
    String observacao
) {}
