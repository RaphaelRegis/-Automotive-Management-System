package com.sistemaAutomotivo.SistemaAutomotivo.modulos.seguranca.dto;

import com.sistemaAutomotivo.SistemaAutomotivo.modulos.seguranca.entities.enums.Role;

public record RegisterDTO(String userName, String password, Role role) {
    
}
