package com.sistemaAutomotivo.SistemaAutomotivo.modulos.clientes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.sistemaAutomotivo.SistemaAutomotivo.modulos.clientes.entities.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    
}
