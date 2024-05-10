package com.sistemaAutomotivo.SistemaAutomotivo.modulos.funcionarios.services;

import java.util.ArrayList;
import java.util.List;

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

        if (funcionarioRepository.existsByCpf(funcionarioDTO.cpf())) {
            throw new RuntimeException("CPF de funcionário já cadastrado!");
        }
        if (funcionarioRepository.existsByEmail(funcionarioDTO.email())) {
            throw new RuntimeException("Email de funcionário já cadastrado!");
        }

        return funcionarioRepository.save(DTOtoFuncionario(funcionarioDTO));
    }

    // READ
    @Override
    public List<Funcionario> findAllFuncionarios() {

        if (funcionarioRepository.findAll().isEmpty()) {
            throw new RuntimeException("Nenhum funcionário encontrado");
        }

        return funcionarioRepository.findAll();
    }

    @Override
    public Funcionario findById(Integer id) {

        if (!(funcionarioRepository.existsById(id))) {
            throw new RuntimeException("Nenhum funcionario encontrado!");
        }

        return funcionarioRepository.findById(id).get();
    }

    @Override
    public List<Equipe> findAllEquipes(String cpf) {
        Funcionario funcionario = findByCpf(cpf);

        if (funcionario.getEquipes().isEmpty()) {
            throw new RuntimeException("Funcionário não está em nenhuma equipe!");
        }

        return funcionario.getEquipes();
    }

    @Override
    public Funcionario findByCpf(String cpf) {
        return funcionarioRepository.findByCpf(cpf)
                .orElseThrow(() -> new RuntimeException("Nenhum funcionario encontrado!"));
    }

    // UPDATE
    @Override
    public Funcionario updateByCpf(String cpf, FuncionarioDTO funcionarioDTO) {
        // pega o funcionario existente com base no cpf
        Funcionario funcionarioExistente = findByCpf(cpf);

        // constroi o funcionario atualizado
        funcionarioExistente.setNome(funcionarioDTO.nome());
        funcionarioExistente.setApelido(funcionarioDTO.apelido());
        funcionarioExistente.setCpf(funcionarioDTO.cpf());
        funcionarioExistente.setEndereco(funcionarioDTO.endereco());
        funcionarioExistente.setOficio(funcionarioDTO.oficio());
        funcionarioExistente.setCargaHorariaSemanal(funcionarioDTO.cargaHorariaSemanal());
        funcionarioExistente.setEmail(funcionarioDTO.email());
        funcionarioExistente.setTelefone(funcionarioDTO.telefone());

        // salva e retorna novamente o funcionario existente
        return funcionarioRepository.save(funcionarioExistente);
    }

    // DELETE
    @Override
    public Funcionario deleteByCpf(String cpf) {
        Funcionario funcionarioExcluido = findByCpf(cpf);

        if (!(funcionarioExcluido.getEquipes().isEmpty())) {
            throw new RuntimeException("Funcionario não pode ser excluído caso faça parte de alguma equipe!");
        }

        funcionarioRepository.delete(funcionarioExcluido);

        return funcionarioExcluido;
    }

    // metodos auxiliares
    Funcionario DTOtoFuncionario(FuncionarioDTO funcionarioDTO) {

        // o funcionario eh instanciado sem fazer parte de nenhuma equipe
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
