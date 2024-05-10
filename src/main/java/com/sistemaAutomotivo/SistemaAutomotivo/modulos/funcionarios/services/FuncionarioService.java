package com.sistemaAutomotivo.SistemaAutomotivo.modulos.funcionarios.services;

import java.util.List;

import com.sistemaAutomotivo.SistemaAutomotivo.modulos.equipes.entities.Equipe;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.funcionarios.dto.FuncionarioDTO;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.funcionarios.entities.Funcionario;

public interface FuncionarioService {
    
    // CREATE
    Funcionario saveFuncionario(FuncionarioDTO funcionarioDTO);

    // READ
    List<Funcionario> findAllFuncionarios();
    Funcionario findById(Integer id);
    List<Equipe> findAllEquipes(String cpf);
    Funcionario findByCpf(String cpf);

    // UPDATE
    Funcionario updateByCpf(String cpf, FuncionarioDTO funcionarioDTO);

    // DELETE
    Funcionario deleteByCpf(String cpf);


}
