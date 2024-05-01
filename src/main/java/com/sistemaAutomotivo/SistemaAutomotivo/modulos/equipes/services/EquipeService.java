package com.sistemaAutomotivo.SistemaAutomotivo.modulos.equipes.services;

import java.util.List;

import com.sistemaAutomotivo.SistemaAutomotivo.modulos.equipes.dto.EquipeDTO;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.equipes.entities.Equipe;
// import com.sistemaAutomotivo.SistemaAutomotivo.modulos.relacionamentos.entities.MembroEquipe;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.funcionarios.entities.Funcionario;

public interface EquipeService {
    
    // CREATE
    public Equipe saveEquipe(EquipeDTO equipe);

    // READ
    public Equipe findById(Integer idEquipe);
    public List<Equipe> findAllEquipes();
    public List<Funcionario> findAllMembros(Integer idEquipe);
    
    // UPDATE
    public Equipe updateEquipeById(Integer idEquipe, EquipeDTO equipeAtualizada);
    public Funcionario adicionarFuncionario(Integer idEquipe, Integer idFuncionario);
    public Funcionario removerFuncionario(Integer idEquipe, Integer idFuncionario);
    
    // DELETE
    public Equipe deleteEquipeById(Integer idEquipe);

    
}
