package com.sistemaAutomotivo.SistemaAutomotivo.modulos.clientes.dto;

import com.sistemaAutomotivo.SistemaAutomotivo.modulos.clientes.entities.enums.TipoCliente;

public record ClienteDTO(
    String nome,
    String cpf_cnpj,
    TipoCliente tipo,
    String telefoneContato,
    String email,
    String endereco,
    String cep
) {}
