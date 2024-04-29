package com.sistemaAutomotivo.SistemaAutomotivo.modulos.relacionamentos.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemaAutomotivo.SistemaAutomotivo.modulos.equipes.entities.Equipe;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.equipes.repositories.EquipeRepository;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.funcionarios.entities.Funcionario;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.funcionarios.repositories.FuncionarioRepository;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.relacionamentos.dto.MembroEquipeDTO;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.relacionamentos.entities.MembroEquipe;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.relacionamentos.repositories.MembroEquipeRepository;

@Service
public class MembroEquipeServiceImpl implements MembroEquipeService {

    @Autowired
    private MembroEquipeRepository membroEquipeRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private EquipeRepository equipeRepository;

    // CREATE
    @Override
    public MembroEquipe salvarMembroEquipe(Integer idEquipe, Integer idFuncionario) {

        Funcionario funcionario = funcionarioRepository.findById(idFuncionario).get();
        Equipe equipe = equipeRepository.findById(idEquipe).get();

        return membroEquipeRepository.save(new MembroEquipe(null, equipe, funcionario));
    }
    
    //READ
    @Override
    public List<MembroEquipe> findByFuncionario(Integer idFuncionario) {

        Funcionario funcionario = funcionarioRepository.findById(idFuncionario).get();
        
        return membroEquipeRepository.findAllByFuncionario(funcionario);
    }

    @Override
    public List<MembroEquipe> findByEquipe(Integer idEquipe) {
        Equipe equipe = equipeRepository.findById(idEquipe).get();
        
        return membroEquipeRepository.findAllByEquipe(equipe);
    }

    @Override
    public MembroEquipe findByEquipeAndFuncionario(Integer idEquipe, Integer idFuncionario) {
        Equipe equipe = equipeRepository.findById(idEquipe).get();
        Funcionario funcionario = funcionarioRepository.findById(idFuncionario).get();
        
        return membroEquipeRepository.findAllByEquipeAndFuncionario(equipe, funcionario);
    }

    @Override
    public List<MembroEquipe> findAll() {
        return membroEquipeRepository.findAll();
    }
    
    // UPDATE
    @Override
    public MembroEquipe updateMembroEquipeById(Integer idMembroEquipe, MembroEquipeDTO membroEquipeDTO) {
        MembroEquipe membroEquipeExistente = membroEquipeRepository.findById(idMembroEquipe).get();
        MembroEquipe membroEquipeAtualizado = DTOtoMembroEquipe(membroEquipeDTO);

        membroEquipeAtualizado.setIdMembroEquipe(idMembroEquipe);

        BeanUtils.copyProperties(membroEquipeAtualizado, membroEquipeExistente);

        return membroEquipeRepository.save(membroEquipeExistente);

    }

    // DELETE
    @Override
    public MembroEquipe deleteMembroEquipeById(Integer idMembroEquipe) {
        MembroEquipe membroEquipe = membroEquipeRepository.findById(idMembroEquipe).get();
        membroEquipeRepository.delete(membroEquipe);

        membroEquipe.setEquipe(null);
        membroEquipe.setFuncionario(null);
        return membroEquipe;
    }

    // metodos auxiliares
    public MembroEquipe DTOtoMembroEquipe(MembroEquipeDTO membroEquipeDTO) {
        Funcionario funcionario = funcionarioRepository.findById(membroEquipeDTO.idFuncionario()).get();
        Equipe equipe = equipeRepository.findById(membroEquipeDTO.idEquipe()).get();

        MembroEquipe membroEquipe = new MembroEquipe(null, equipe, funcionario);
        return membroEquipe;
    }
    

    

    
    
}
