package com.sistemaAutomotivo.SistemaAutomotivo.modulos.veiculos.services;

import java.util.List;


import com.sistemaAutomotivo.SistemaAutomotivo.modulos.veiculos.dto.VeiculoDTO;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.veiculos.entities.Veiculo;

public interface VeiculoService {
    
    // CREATE
    Veiculo saveVeiculo(VeiculoDTO veiculoDTO);
    
    // READ
    List<Veiculo> findAllVeiculos();
    Veiculo findByPlaca(String placa);
    
    // UPDATE
    Veiculo updateByPlaca(String placa, VeiculoDTO veiculoDTO);
    
    // DELETE
    Veiculo deleteByPlaca(String placa);
}
