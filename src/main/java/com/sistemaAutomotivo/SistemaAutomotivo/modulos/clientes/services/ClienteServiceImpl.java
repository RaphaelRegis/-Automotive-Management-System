package com.sistemaAutomotivo.SistemaAutomotivo.modulos.clientes.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemaAutomotivo.SistemaAutomotivo.modulos.clientes.dto.ClienteDTO;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.clientes.entities.Cliente;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.clientes.repositories.ClienteRepository;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.veiculos.entities.Veiculo;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    // CREATE
    @Override
    public Cliente saveCliente(ClienteDTO clienteDTO) {

        if (clienteRepository.existsByCpfCnpj(clienteDTO.cpf_cnpj())) {
            throw new RuntimeException("CPF de cliente já cadastrado!");
        }
        if (clienteRepository.existsByEmail(clienteDTO.email())) {
            throw new RuntimeException("Email de cliente já cadastrado!");
        }

        return clienteRepository.save(DTOtoCliente(clienteDTO));
    }

    // READ
    @Override
    public List<Cliente> findAllClientes() {

        if (clienteRepository.findAll().isEmpty()) {
            throw new RuntimeException("Nenhum cliente encontrado");
        }

        return clienteRepository.findAll();
    }

    @Override
    public Cliente findByCpfCnpj(String cpfCnpj) {
        return clienteRepository.findByCpfCnpj(cpfCnpj)
                .orElseThrow(() -> new RuntimeException("Nenhum cliente com esse CPF/CNPJ encontrado!"));
    }

    @Override
    public List<Veiculo> findVeiculos(String cpfCnpj) {
        Cliente cliente = findByCpfCnpj(cpfCnpj);

        if (cliente.getVeiculos().isEmpty()) {
            throw new RuntimeException("Esse cliente ainda não possui veículos!");
        }

        return cliente.getVeiculos();
    }

    // UPDATE
    @Override
    public Cliente updateByCpfCnpj(String cpfCnpj, ClienteDTO clienteDTO) {
        // pega o cliente existente com base no cpf/cnpj
        Cliente clienteExistente = findByCpfCnpj(cpfCnpj);

        // constroi o cliente atualizado
        clienteExistente.setNome(clienteDTO.nome());
        clienteExistente.setCpfCnpj(clienteDTO.cpf_cnpj());
        clienteExistente.setTipo(clienteDTO.tipo());
        clienteExistente.setTelefoneContato(clienteDTO.telefoneContato());
        clienteExistente.setEmail(clienteDTO.email());
        clienteExistente.setEndereco(clienteDTO.endereco());
        clienteExistente.setCep(clienteDTO.cep());

        // salva e retorna novamente o cliente existente
        return clienteRepository.save(clienteExistente);
    }

    // DELETE
    @Override
    public Cliente deleteByCpfCnpj(String cpfCnpj) {
        Cliente clienteExcluido = findByCpfCnpj(cpfCnpj);

        clienteRepository.delete(clienteExcluido);

        return clienteExcluido;
    }

    // metodos auxiliares
    Cliente DTOtoCliente(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente(
                null,
                clienteDTO.nome(),
                clienteDTO.cpf_cnpj(),
                clienteDTO.tipo(),
                clienteDTO.telefoneContato(),
                clienteDTO.email(),
                clienteDTO.endereco(),
                clienteDTO.cep(),
                new ArrayList<>());

        return cliente;
    }

}
