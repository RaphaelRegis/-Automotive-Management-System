package com.sistemaAutomotivo.SistemaAutomotivo.modulos.orcamentos.dto;

import java.time.LocalDate;

import com.sistemaAutomotivo.SistemaAutomotivo.modulos.orcamentos.entities.enums.Status;

public record OrcamentoDTO(
    LocalDate data,
    Status status,
    String cpfResponsavel,
    String placaVeiculo
) {}
