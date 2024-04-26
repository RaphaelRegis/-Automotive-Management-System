package com.sistemaAutomotivo.SistemaAutomotivo.modulos.clientes.services;

import java.util.List;


import com.sistemaAutomotivo.SistemaAutomotivo.modulos.clientes.dto.ClienteDTO;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.clientes.entities.Cliente;

public interface ClienteService {
    
    Cliente saveCliente(ClienteDTO clienteDTO);

    List<Cliente> findAllClientes();

    Cliente findById(Integer id);

    Cliente updateById(Integer id, ClienteDTO clienteDTO);

    Cliente deleteById(Integer id);
}
