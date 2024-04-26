package com.sistemaAutomotivo.SistemaAutomotivo.modulos.funcionarios.dto;


public record FuncionarioDTO(
    String nome,
    String apelido,
    String cpf,
    String endereco,
    String oficio,
    int cargaHorariaSemanal,
    String email,
    String telefone  
) {}
