package com.sistemaAutomotivo.SistemaAutomotivo.modulos.veiculos.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistemaAutomotivo.SistemaAutomotivo.modulos.veiculos.dto.VeiculoDTO;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.veiculos.entities.Veiculo;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.veiculos.services.VeiculoService;

@RestController
@RequestMapping("/api/veiculos")
public class VeiculoController {

    @Autowired
    private VeiculoService veiculoService;

    // CREATE
    @PostMapping()
    public ResponseEntity<Veiculo> saveVeiculo(@RequestBody VeiculoDTO veiculoDTO) {
        return ResponseEntity.ok(veiculoService.saveVeiculo(veiculoDTO));
    }

    // READ
    @GetMapping()
    public ResponseEntity<List<Veiculo>> findAllVeiculos() {
        return ResponseEntity.ok(veiculoService.findAllVeiculos());
    }

    @GetMapping("/{placa}")
    public ResponseEntity<Veiculo> findVeiculoByPlaca(@PathVariable("placa") String placa) {
        return ResponseEntity.ok(veiculoService.findByPlaca(placa));
    }

    // UPDATE
    @PatchMapping("/{placa}")
    public ResponseEntity<Veiculo> updateVeiculoByPlaca(@PathVariable("placa") String placa, @RequestBody VeiculoDTO veiculoDTO) {
        return ResponseEntity.ok(veiculoService.updateByPlaca(placa, veiculoDTO));
    }

    // DELETE
    @DeleteMapping("/{placa}")
    public ResponseEntity<Veiculo> deleteVeiculoByPlaca(@PathVariable("placa") String placa) {
        return ResponseEntity.ok(veiculoService.deleteByPlaca(placa));
    }

}
