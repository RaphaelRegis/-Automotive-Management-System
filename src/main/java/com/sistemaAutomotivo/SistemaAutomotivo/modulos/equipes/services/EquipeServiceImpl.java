package com.sistemaAutomotivo.SistemaAutomotivo.modulos.equipes.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemaAutomotivo.SistemaAutomotivo.modulos.equipes.dto.EquipeDTO;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.equipes.entities.Equipe;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.equipes.repositories.EquipeRepository;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.funcionarios.entities.Funcionario;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.funcionarios.repositories.FuncionarioRepository;

@Service
public class EquipeServiceImpl implements EquipeService {

    @Autowired
    private EquipeRepository equipeRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    // CREATE
    @Override
    public Equipe saveEquipe(EquipeDTO equipe) {
        return equipeRepository.save(DTOtoEquipe(equipe));
    }

    // READ
    @Override
    public Equipe findById(Integer idEquipe) {
        return equipeRepository.findById(idEquipe).get();
    }

    @Override
    public List<Equipe> findAllEquipes() {
        return equipeRepository.findAll();
    }

    @Override
    public List<Funcionario> findAllMembros(Integer idEquipe) {
        Equipe equipe = equipeRepository.findById(idEquipe).get();

        return equipe.getMembros();
    }

    // UPDATE
    @Override
    public Equipe updateEquipeById(Integer idEquipe, EquipeDTO equipeDTO) {
        // pega a equipe existente com base no id
        Equipe equipeExistente = findById(idEquipe);

        // constroi a equipe atualizada
        Equipe equipeAtualizada = DTOtoEquipe(equipeDTO);
        equipeAtualizada.setId_equipe(equipeExistente.getId_equipe());

        // copia as propriedades da equipe atualizada para a existente
        BeanUtils.copyProperties(equipeAtualizada, equipeExistente);

        // salva e retorna novamente a equipe existente
        return equipeRepository.save(equipeExistente);
    }

    @Override
    public Funcionario adicionarFuncionario(Integer idEquipe, Integer idFuncionario) {
        Equipe equipeExistente = equipeRepository.findById(idEquipe).get();
        Funcionario funcionario = funcionarioRepository.findById(idFuncionario).get();

        equipeExistente.getMembros().add(funcionario);
        equipeRepository.save(equipeExistente);

        return funcionario;
    }

    @Override
    public Funcionario removerFuncionario(Integer idEquipe, Integer idFuncionario) {
        Equipe equipeExistente = equipeRepository.findById(idEquipe).get();
        Funcionario funcionario = funcionarioRepository.findById(idFuncionario).get();

        equipeExistente.getMembros().remove(funcionario);

        equipeRepository.save(equipeExistente);

        return funcionario;
    }

    // DELETE
    @Override
    public Equipe deleteEquipeById(Integer idEquipe) {
        Equipe equipe = equipeRepository.findById(idEquipe).get();
        equipeRepository.delete(equipe);
        return equipe;
    }

    // metodos auxiliares
    public Equipe DTOtoEquipe(EquipeDTO equipeDTO) {

        Funcionario funcionario = funcionarioRepository
                .findById(equipeDTO.idResponsavel())
                .get();

        List<Funcionario> membros = new ArrayList<>();
        membros.add(funcionario);

        Equipe equipe = new Equipe(null,
                equipeDTO.nome(),
                equipeDTO.setor(),
                funcionario,
                membros,
                new ArrayList<>());

        return equipe;
    }

    
}
