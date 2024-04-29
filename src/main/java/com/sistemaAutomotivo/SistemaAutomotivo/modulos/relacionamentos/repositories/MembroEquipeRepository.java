package com.sistemaAutomotivo.SistemaAutomotivo.modulos.relacionamentos.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sistemaAutomotivo.SistemaAutomotivo.modulos.equipes.entities.Equipe;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.funcionarios.entities.Funcionario;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.relacionamentos.entities.MembroEquipe;

@Repository
public interface MembroEquipeRepository extends JpaRepository<MembroEquipe, Integer>{
    
    List<MembroEquipe> findAllByFuncionario(Funcionario funcionario);
    List<MembroEquipe> findAllByEquipe(Equipe equipe);
    MembroEquipe findAllByEquipeAndFuncionario(Equipe equipe, Funcionario funcionario);

}
