package com.sistemaAutomotivo.SistemaAutomotivo.modulos.equipes.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemaAutomotivo.SistemaAutomotivo.modulos.equipes.dto.EquipeDTO;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.equipes.entities.Equipe;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.equipes.repositories.EquipeRepository;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.funcionarios.entities.Funcionario;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.funcionarios.services.FuncionarioService;

@Service
public class EquipeServiceImpl implements EquipeService {

    @Autowired
    private EquipeRepository equipeRepository;

    @Autowired
    private FuncionarioService funcionarioService;

    // CREATE
    @Override
    public Equipe saveEquipe(EquipeDTO equipeDTO) {

        /* verifica se o nome da equipe já existe */
        if (equipeRepository.existsByNome(equipeDTO.nome())) {
            throw new RuntimeException("Nome de equipe já cadastrado!");
        }

        Equipe novaEquipe = DTOtoEquipe(equipeDTO);

        /* pega o funcionario responsavel e adiciona a equipe */
        Funcionario funcionarioResponsavel = funcionarioService.findByCpf(equipeDTO.cpfResponsavel());
        novaEquipe.setResponsavel(funcionarioResponsavel);
        novaEquipe.getMembros().add(funcionarioResponsavel);

        return equipeRepository.save(novaEquipe);
    }

    // READ
    @Override
    public Equipe findById(Integer idEquipe) {

        if (!(equipeRepository.existsById(idEquipe))) {
            throw new RuntimeException("Nenhuma equipe com esse id encontrada!");
        }

        return equipeRepository.findById(idEquipe).get();
    }

    @Override
    public List<Equipe> findAllEquipes() {

        if (equipeRepository.findAll().isEmpty()) {
            throw new RuntimeException("Nenhuma equipe encontrada!");
        }

        return equipeRepository.findAll();
    }

    @Override
    public List<Funcionario> findAllMembros(String nome) {
        Equipe equipe = findByNome(nome);

        return equipe.getMembros();
    }

    @Override
    public Equipe findByNome(String nome) {

        nome = nome.replace("_", " ");

        return equipeRepository.findByNome(nome)
                .orElseThrow(() -> new RuntimeException("Nenhuma equipe com esse nome foi encontrada!"));
    }

    // UPDATE
    @Override
    public Equipe updateEquipeByNome(String nome, EquipeDTO equipeDTO) {
        // pega a equipe existente com base no nome
        Equipe equipeExistente = findByNome(nome);

        // constroi a equipe atualizada
        /*
         * Equipe equipeAtualizada = DTOtoEquipeUpdate(equipeDTO);
         * equipeAtualizada.setId_equipe(equipeExistente.getId_equipe());
         */

        // atualiza nome e setor
        equipeExistente.setNome(equipeDTO.nome());
        equipeExistente.setSetor(equipeDTO.setor());

        // caso seja definido um novo responsavel
        if (equipeExistente.getResponsavel().getCpf() != equipeDTO.cpfResponsavel()) {
            Funcionario novoResponsavel = funcionarioService.findByCpf(equipeDTO.cpfResponsavel());
            equipeExistente.setResponsavel(novoResponsavel);

            if (!(equipeExistente.getMembros().contains(novoResponsavel))) {
                equipeExistente.getMembros().add(novoResponsavel);
            }
        }

        // copia as propriedades da equipe atualizada para a existente
        // BeanUtils.copyProperties(equipeAtualizada, equipeExistente);

        // salva e retorna novamente a equipe existente
        return equipeRepository.save(equipeExistente);
    }

    @Override
    public Funcionario adicionarFuncionario(String nome, String cpfFuncionario) {
        Equipe equipeExistente = findByNome(nome);
        Funcionario funcionario = funcionarioService.findByCpf(cpfFuncionario);

        if (equipeExistente.getMembros().contains(funcionario)) {
            throw new RuntimeException("Funcionario já é parte da equipe!");
        }

        equipeExistente.getMembros().add(funcionario);
        equipeRepository.save(equipeExistente);

        return funcionario;
    }

    @Override
    public Funcionario removerFuncionario(String nome, String cpfFuncionario) {
        Equipe equipeExistente = findByNome(nome);
        Funcionario funcionario = funcionarioService.findByCpf(cpfFuncionario);

        if (equipeExistente.getResponsavel().getCpf() == funcionario.getCpf()) {
            throw new RuntimeException("O líder da equipe não pode ser excluído!");
        }

        equipeExistente.getMembros().remove(funcionario);

        equipeRepository.save(equipeExistente);

        return funcionario;
    }

    // DELETE
    @Override
    public Equipe deleteEquipeByNome(String nome) {
        Equipe equipe = findByNome(nome);

        if (!(equipe.getServicos_orcamentos().isEmpty())) {
            throw new RuntimeException("Equipe não pode ser excluída caso seja responsavel por um Servico_Orcamento!");
        }

        equipeRepository.delete(equipe);
        return equipe;
    }

    // metodos auxiliares
    public Equipe DTOtoEquipe(EquipeDTO equipeDTO) {

        Equipe equipe = new Equipe(null,
                equipeDTO.nome(),
                equipeDTO.setor(),
                null,
                new ArrayList<>(),
                new ArrayList<>());

        return equipe;
    }

    public Equipe DTOtoEquipeUpdate(EquipeDTO equipeDTO) {
        return null;
    }

}
