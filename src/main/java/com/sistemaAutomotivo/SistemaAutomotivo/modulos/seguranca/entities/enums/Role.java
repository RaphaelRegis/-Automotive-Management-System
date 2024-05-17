package com.sistemaAutomotivo.SistemaAutomotivo.modulos.seguranca.entities.enums;

public enum Role {
    ADMIN("admin"),
    FUNCIONARIO("funcionario"),
    CLIENTE("cliente");

    private String role;

    Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return this.role;
    }
}
