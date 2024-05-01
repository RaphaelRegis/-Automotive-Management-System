package com.sistemaAutomotivo.SistemaAutomotivo.modulos.funcionarios.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemaAutomotivo.SistemaAutomotivo.modulos.equipes.entities.Equipe;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.funcionarios.dto.FuncionarioDTO;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.funcionarios.entities.Funcionario;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.funcionarios.repositories.FuncionarioRepository;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {
    
    @Autowired
    private FuncionarioRepository funcionarioRepository;
    
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

    @Override
    public List<Equipe> findAllEquipes(Integer idFuncionario) {
        Funcionario funcionario = funcionarioRepository.findById(idFuncionario).get();

        return funcionario.getEquipes();
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

    // DELETE
    @Override
    public Funcionario deleteById(Integer id) {
        Funcionario funcionarioExcluido = funcionarioRepository.findById(id).get();

        funcionarioRepository.delete(funcionarioExcluido);

        return funcionarioExcluido;
    }

    // metodos auxiliares
    Funcionario DTOtoFuncionario(FuncionarioDTO funcionarioDTO) {

        // o funcionario eh instanciado sem fazer de nenhuma equipe
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
            new ArrayList<>());

        return funcionario;
    }
}
