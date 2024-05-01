package com.sistemaAutomotivo.SistemaAutomotivo.modulos.clientes.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
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
        return clienteRepository.save(DTOtoCliente(clienteDTO));
    }

    // READ
    @Override
    public List<Cliente> findAllClientes() {
       // colocar if isEmpty
        return clienteRepository.findAll();
    }

    @Override
    public Cliente findById(Integer id) {
        return clienteRepository.findById(id).get();
    }

    @Override
    public List<Veiculo> findVeiculos(Integer idCliente) {
        Cliente cliente = findById(idCliente);

        return cliente.getVeiculos();
    }

    // UPDATE
    @Override
    public Cliente updateById(Integer id, ClienteDTO clienteDTO) {
        // pega o cliente existente com base no id
        Cliente clienteExistente = findById(id);
        
        // constroi o cliente atualizado
        Cliente clienteAtualizado = DTOtoCliente(clienteDTO);
        clienteAtualizado.setId_cliente(clienteExistente.getId_cliente());

        // copia as propriedades do cliente atualizado para o existente
        BeanUtils.copyProperties(clienteAtualizado, clienteExistente);

        //salva e retorna novamente o cliente existente
        return clienteRepository.save(clienteExistente);
    }

    // DELETE
    @Override
    public Cliente deleteById(Integer id) {
        Cliente clienteExcluido = clienteRepository.findById(id).get();

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
