package com.sistemaAutomotivo.SistemaAutomotivo.modulos.equipes.dto;

import java.util.List;

import com.sistemaAutomotivo.SistemaAutomotivo.modulos.funcionarios.entities.Funcionario;

public record GetEquipeDTO(
    Integer id_equipe,
    String nome,
    String setor,
    Funcionario responsavel,
    List<Integer> idMembros,
    List<Integer> servicos_orcamentos
) {}
