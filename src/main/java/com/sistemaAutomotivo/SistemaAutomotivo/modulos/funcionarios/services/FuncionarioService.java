package com.sistemaAutomotivo.SistemaAutomotivo.modulos.funcionarios.services;

import java.util.List;


import com.sistemaAutomotivo.SistemaAutomotivo.modulos.funcionarios.dto.FuncionarioDTO;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.funcionarios.entities.Funcionario;

public interface FuncionarioService {
    
    Funcionario saveFuncionario(FuncionarioDTO funcionarioDTO);

    List<Funcionario> findAllFuncionarios();

    Funcionario findById(Integer id);

    Funcionario updateById(Integer id, FuncionarioDTO funcionarioDTO);

    Funcionario deleteById(Integer id);
}
