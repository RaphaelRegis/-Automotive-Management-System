package com.sistemaAutomotivo.SistemaAutomotivo.modulos.equipes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sistemaAutomotivo.SistemaAutomotivo.modulos.equipes.entities.Equipe;

@Repository
public interface EquipeRepository extends JpaRepository<Equipe, Integer> {
    
}
