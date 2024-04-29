package com.sistemaAutomotivo.SistemaAutomotivo.modulos.relacionamentos.services;

import java.util.List;

import com.sistemaAutomotivo.SistemaAutomotivo.modulos.relacionamentos.dto.MembroEquipeDTO;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.relacionamentos.entities.MembroEquipe;

public interface MembroEquipeService {
    
    // CREATE
    public MembroEquipe salvarMembroEquipe(Integer idEquipe, Integer idFuncionario);

    // READ
    public List<MembroEquipe> findByFuncionario(Integer idFuncionario);
    public List<MembroEquipe> findByEquipe(Integer idEquipe);
    public MembroEquipe findByEquipeAndFuncionario(Integer idEquipe, Integer idFuncionario);
    public List<MembroEquipe> findAll();

    // UPDATE
    public MembroEquipe updateMembroEquipeById(Integer idMembroEquipe, MembroEquipeDTO membroEquipe);

    // DELETE
    public MembroEquipe deleteMembroEquipeById(Integer idMembroEquipe);
}
