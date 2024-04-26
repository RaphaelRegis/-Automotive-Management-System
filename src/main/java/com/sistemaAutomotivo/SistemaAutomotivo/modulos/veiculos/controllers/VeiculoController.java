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

    @PostMapping()
    public ResponseEntity<Veiculo> saveVeiculo(@RequestBody VeiculoDTO veiculoDTO) {
        return ResponseEntity.ok(veiculoService.saveVeiculo(veiculoDTO));
    }

    @GetMapping()
    public ResponseEntity<List<Veiculo>> findAllVeiculos() {
        return ResponseEntity.ok(veiculoService.findAllVeiculos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Veiculo> findVeiculoById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(veiculoService.findById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Veiculo> updateVeiculoById(@PathVariable("id") Integer id, @RequestBody VeiculoDTO veiculoDTO) {
        return ResponseEntity.ok(veiculoService.updateById(id, veiculoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Veiculo> deleteVeiculoById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(veiculoService.deleteById(id));
    }

}
