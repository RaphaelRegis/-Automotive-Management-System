package com.sistemaAutomotivo.SistemaAutomotivo.modulos.equipes.services;

import java.util.List;

import com.sistemaAutomotivo.SistemaAutomotivo.modulos.equipes.dto.EquipeDTO;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.equipes.entities.Equipe;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.funcionarios.entities.Funcionario;

public interface EquipeService {
    
    // CREATE
    public Equipe saveEquipe(EquipeDTO equipe);

    // READ
    public Equipe findById(Integer idEquipe);
    public List<Equipe> findAllEquipes();
    public List<Funcionario> findAllMembros(String nome);
    public Equipe findByNome(String nome);
    
    // UPDATE
    public Equipe updateEquipeByNome(String nome, EquipeDTO equipeAtualizada);
    public Funcionario adicionarFuncionario(String nome, String cpfFuncionario);
    public Funcionario removerFuncionario(String nome, String cpfFuncionario);
    
    // DELETE
    public Equipe deleteEquipeByNome(String nome);

    
}
