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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/veiculos")
@Tag(name = "Módulo Veículos")
@SecurityRequirement(name = "bearerAuth")
public class VeiculoController {

    @Autowired
    private VeiculoService veiculoService;

    // CREATE
    @PostMapping()
    @Operation(
        summary = "Salvar Veículo"
    )
    public ResponseEntity<Veiculo> saveVeiculo(@RequestBody VeiculoDTO veiculoDTO) {
        return ResponseEntity.ok(veiculoService.saveVeiculo(veiculoDTO));
    }

    // READ
    @GetMapping()
    @Operation(
        summary = "Encontrar todos os Veículos"
    )
    public ResponseEntity<List<Veiculo>> findAllVeiculos() {
        return ResponseEntity.ok(veiculoService.findAllVeiculos());
    }

    @GetMapping("/{placa}")
    @Operation(
        summary = "Encontrar Veículo por placa"
    )
    public ResponseEntity<Veiculo> findVeiculoByPlaca(@PathVariable("placa") String placa) {
        return ResponseEntity.ok(veiculoService.findByPlaca(placa));
    }

    // UPDATE
    @PatchMapping("/{placa}")
    @Operation(
        summary = "Atualizar Veículo por placa"
    )
    public ResponseEntity<Veiculo> updateVeiculoByPlaca(@PathVariable("placa") String placa, @RequestBody VeiculoDTO veiculoDTO) {
        return ResponseEntity.ok(veiculoService.updateByPlaca(placa, veiculoDTO));
    }

    // DELETE
    @DeleteMapping("/{placa}")
    @Operation(
        summary = "Deletar Veículo por placa"
    )
    public ResponseEntity<Veiculo> deleteVeiculoByPlaca(@PathVariable("placa") String placa) {
        return ResponseEntity.ok(veiculoService.deleteByPlaca(placa));
    }

}
