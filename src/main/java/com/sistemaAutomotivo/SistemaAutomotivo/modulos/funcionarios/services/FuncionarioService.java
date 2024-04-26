package com.sistemaAutomotivo.SistemaAutomotivo.modulos.funcionarios.services;

import java.util.List;


import com.sistemaAutomotivo.SistemaAutomotivo.modulos.funcionarios.dto.FuncionarioDTO;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.funcionarios.entities.Funcionario;

public interface FuncionarioService {
    
    // CREATE
    Funcionario saveFuncionario(FuncionarioDTO funcionarioDTO);

    // READ
    List<Funcionario> findAllFuncionarios();

    Funcionario findById(Integer id);

    // UPDATE
    Funcionario updateById(Integer id, FuncionarioDTO funcionarioDTO);

    Funcionario definirLiderEquipe(Integer idFuncionario, Integer idEquipe);
    
    Funcionario integrarEquipe(Integer idFuncionario, Integer idEquipe);

    // DELETE
    Funcionario deleteById(Integer id);

}
