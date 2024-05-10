package com.sistemaAutomotivo.SistemaAutomotivo.modulos.clientes.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.sistemaAutomotivo.SistemaAutomotivo.modulos.clientes.entities.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    Optional<Cliente> findByCpfCnpj(String cpfCnpj);
    boolean existsByCpfCnpj(String cpfCnpj);
    boolean existsByEmail(String email);
}
