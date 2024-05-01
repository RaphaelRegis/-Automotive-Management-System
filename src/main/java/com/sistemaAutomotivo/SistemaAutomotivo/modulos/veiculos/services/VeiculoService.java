package com.sistemaAutomotivo.SistemaAutomotivo.modulos.veiculos.services;

import java.util.List;


import com.sistemaAutomotivo.SistemaAutomotivo.modulos.veiculos.dto.VeiculoDTO;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.veiculos.entities.Veiculo;

public interface VeiculoService {
    
    // CREATE
    Veiculo saveVeiculo(VeiculoDTO veiculoDTO);
    
    // READ
    List<Veiculo> findAllVeiculos();
    Veiculo findById(Integer id);
    
    // UPDATE
    Veiculo updateById(Integer id, VeiculoDTO veiculoDTO);
    
    // DELETE
    Veiculo deleteById(Integer id);
}
