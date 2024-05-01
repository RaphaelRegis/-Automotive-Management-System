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
    Cliente findById(Integer id);
    List<Veiculo> findVeiculos(Integer idCliente);
    
    // UPDATE
    Cliente updateById(Integer id, ClienteDTO clienteDTO);
    
    // DELETE
    Cliente deleteById(Integer id);

}
