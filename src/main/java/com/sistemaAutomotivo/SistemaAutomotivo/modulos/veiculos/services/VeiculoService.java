package com.sistemaAutomotivo.SistemaAutomotivo.modulos.veiculos.services;

import java.util.List;


import com.sistemaAutomotivo.SistemaAutomotivo.modulos.veiculos.dto.VeiculoDTO;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.veiculos.entities.Veiculo;

public interface VeiculoService {
    
    Veiculo saveVeiculo(VeiculoDTO veiculoDTO);

    List<Veiculo> findAllVeiculos();

    Veiculo findById(Integer id);

    Veiculo updateById(Integer id, VeiculoDTO veiculoDTO);

    Veiculo deleteById(Integer id);
}
