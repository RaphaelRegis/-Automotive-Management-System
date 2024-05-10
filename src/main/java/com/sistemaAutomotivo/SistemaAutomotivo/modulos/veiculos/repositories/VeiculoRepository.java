package com.sistemaAutomotivo.SistemaAutomotivo.modulos.veiculos.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sistemaAutomotivo.SistemaAutomotivo.modulos.veiculos.entities.Veiculo;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Integer>{
    Optional<Veiculo> findByPlaca(String placa);
    boolean existsByPlaca(String placa);
}
