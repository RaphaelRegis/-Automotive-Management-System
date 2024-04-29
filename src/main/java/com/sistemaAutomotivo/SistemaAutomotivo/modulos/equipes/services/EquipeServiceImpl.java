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
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.relacionamentos.entities.MembroEquipe;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.relacionamentos.services.MembroEquipeService;

@Service
public class EquipeServiceImpl implements EquipeService {

    @Autowired
    private EquipeRepository equipeRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private MembroEquipeService membroEquipeService;

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
    public MembroEquipe adicionarFuncionario(Integer idEquipe, Integer idFuncionario) {
        return membroEquipeService.salvarMembroEquipe(idEquipe, idFuncionario);
    }

    @Override
    public MembroEquipe removerFuncionario(Integer idEquipe, Integer idFuncionario) {
        MembroEquipe membroEquipe = membroEquipeService.findByEquipeAndFuncionario(idEquipe, idFuncionario);
        return membroEquipeService.deleteMembroEquipeById(membroEquipe.getIdMembroEquipe());
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

        Equipe equipe = new Equipe(null,
                equipeDTO.nome(),
                equipeDTO.setor(),
                funcionario,
                new ArrayList<>(),
                new ArrayList<>());

        return equipe;
    }
}
