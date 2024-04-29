package com.sistemaAutomotivo.SistemaAutomotivo.modulos.equipes.services;

import java.util.List;

import com.sistemaAutomotivo.SistemaAutomotivo.modulos.equipes.dto.EquipeDTO;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.equipes.entities.Equipe;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.relacionamentos.entities.MembroEquipe;

public interface EquipeService {
    
    // CREATE
    public Equipe saveEquipe(EquipeDTO equipe);

    // READ
    public Equipe findById(Integer idEquipe);
    public List<Equipe> findAllEquipes();
    
    // UPDATE
    public Equipe updateEquipeById(Integer idEquipe, EquipeDTO equipeAtualizada);
    public MembroEquipe adicionarFuncionario(Integer idEquipe, Integer idFuncionario);
    public MembroEquipe removerFuncionario(Integer idEquipe, Integer idFuncionario);
    
    // DELETE
    public Equipe deleteEquipeById(Integer idEquipe);
    
}
