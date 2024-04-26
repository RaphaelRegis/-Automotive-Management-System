package com.sistemaAutomotivo.SistemaAutomotivo.modulos.funcionarios.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sistemaAutomotivo.SistemaAutomotivo.modulos.funcionarios.entities.Funcionario;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {
    
}
