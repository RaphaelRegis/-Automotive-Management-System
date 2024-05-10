package com.sistemaAutomotivo.SistemaAutomotivo.modulos.orcamentos.dto;

import java.util.List;

import com.sistemaAutomotivo.SistemaAutomotivo.modulos.relacionamentos.entities.ProdutoOrcamento;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.relacionamentos.entities.ServicoOrcamento;

public record ValorOrcamentoDTO(
    Integer idOrcamento,
    List<ProdutoOrcamento> produtos,
    List<ServicoOrcamento> servicos,
    double valorTotal
) {}
