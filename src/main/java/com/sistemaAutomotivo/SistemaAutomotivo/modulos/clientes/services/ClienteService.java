package com.sistemaAutomotivo.SistemaAutomotivo.modulos.clientes.services;

import java.util.List;


import com.sistemaAutomotivo.SistemaAutomotivo.modulos.clientes.dto.ClienteDTO;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.clientes.entities.Cliente;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.veiculos.entities.Veiculo;

public interface ClienteService {
    
    // CREATE
    Cliente saveCliente(ClienteDTO clienteDTO);
    
    // READ
    List<Cliente> findAllClientes();
    Cliente findByCpfCnpj(String cpfCnpj);
    List<Veiculo> findVeiculos(String cpfCnpj);
    
    // UPDATE
    Cliente updateByCpfCnpj(String cpfCnpj, ClienteDTO clienteDTO);
    
    // DELETE
    Cliente deleteByCpfCnpj(String cpfCnpj);

}
