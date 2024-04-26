package com.sistemaAutomotivo.SistemaAutomotivo.modulos.funcionarios.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemaAutomotivo.SistemaAutomotivo.modulos.equipes.entities.Equipe;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.equipes.repositories.EquipeRepository;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.funcionarios.dto.FuncionarioDTO;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.funcionarios.entities.Funcionario;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.funcionarios.repositories.FuncionarioRepository;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {
    
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private EquipeRepository equipeRepository;
    
    // CREATE
    @Override
    public Funcionario saveFuncionario(FuncionarioDTO funcionarioDTO) {
        // colocar if existsByCPF
        return funcionarioRepository.save(DTOtoFuncionario(funcionarioDTO));
    }

    // READ
    @Override
    public List<Funcionario> findAllFuncionarios() {
       // colocar if isEmpty
        return funcionarioRepository.findAll();
    }

    @Override
    public Funcionario findById(Integer id) {
        return funcionarioRepository.findById(id).get();
    }

    // UPDATE
    @Override
    public Funcionario updateById(Integer id, FuncionarioDTO funcionarioDTO) {
        // pega o funcionario existente com base no id
        Funcionario funcionarioExistente = findById(id);
        
        // constroi o funcionario atualizado
        Funcionario funcionarioAtualizado = DTOtoFuncionario(funcionarioDTO);
        funcionarioAtualizado.setId_funcionario(funcionarioExistente.getId_funcionario());

        // copia as propriedades do funcionario atualizado para o existente
        BeanUtils.copyProperties(funcionarioAtualizado, funcionarioExistente);

        //salva e retorna novamente o funcionario existente
        return funcionarioRepository.save(funcionarioExistente);
    }

    public Funcionario updateById(Integer idFuncionario, Funcionario funcionario) {
        // pega o funcionario existente com base no id
        Funcionario funcionarioExistente = findById(idFuncionario);
        
        // copia as propriedades do funcionario atualizado para o existente
        BeanUtils.copyProperties(funcionario, funcionarioExistente);

        //salva e retorna novamente o funcionario existente
        return funcionarioRepository.save(funcionarioExistente);
    }

    @Override
    public Funcionario definirLiderEquipe(Integer idFuncionario, Integer idEquipe) {
        Equipe equipe = equipeRepository.findById(idEquipe).get();

        Funcionario funcionario = funcionarioRepository.findById(idFuncionario).get();
        funcionario.setEquipeLiderada(equipe);

        return updateById(idFuncionario, funcionario);

    }

    @Override
    public Funcionario integrarEquipe(Integer idFuncionario, Integer idEquipe) {
        Equipe equipe = equipeRepository.findById(idEquipe).get();

        Funcionario funcionario = funcionarioRepository.findById(idFuncionario).get();
        funcionario.getEquipes().add(equipe);

        return updateById(idFuncionario, funcionario);
    }

    // DELETE
    @Override
    public Funcionario deleteById(Integer id) {
        Funcionario funcionarioExcluido = funcionarioRepository.findById(id).get();

        funcionarioRepository.delete(funcionarioExcluido);

        return funcionarioExcluido;
    }

    // metodos auxiliares
    Funcionario DTOtoFuncionario(FuncionarioDTO funcionarioDTO) {

        // o funcionario eh instanciado sem fazer parte e nem liderar nenhuma equipe
        Funcionario funcionario = new Funcionario(
            null,
            funcionarioDTO.nome(),
            funcionarioDTO.apelido(),
            funcionarioDTO.cpf(),
            funcionarioDTO.endereco(),
            funcionarioDTO.oficio(),
            funcionarioDTO.cargaHorariaSemanal(),
            funcionarioDTO.email(),
            funcionarioDTO.telefone(),
            null,
            null);

        return funcionario;
    }








}
