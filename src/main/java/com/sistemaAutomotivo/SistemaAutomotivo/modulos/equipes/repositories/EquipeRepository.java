package com.sistemaAutomotivo.SistemaAutomotivo.modulos.equipes.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sistemaAutomotivo.SistemaAutomotivo.modulos.equipes.entities.Equipe;

@Repository
public interface EquipeRepository extends JpaRepository<Equipe, Integer> {
    Optional<Equipe> findByNome(String nome);
    boolean existsByNome(String nome);
}
