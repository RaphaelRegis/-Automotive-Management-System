package com.sistemaAutomotivo.SistemaAutomotivo.modulos.equipes.controllers;

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

import com.sistemaAutomotivo.SistemaAutomotivo.modulos.equipes.dto.EquipeDTO;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.equipes.entities.Equipe;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.equipes.services.EquipeService;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.funcionarios.entities.Funcionario;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/equipes")
@Tag(name = "Módulo Equipes")
@SecurityRequirement(name = "bearerAuth")
public class EquipeController {
    
    @Autowired
    private EquipeService equipeService;

    // CREATE
    @PostMapping()
    @Operation(
        summary = "Salvar Equipe"
    )
    public ResponseEntity<Equipe> saveEquipe(@RequestBody EquipeDTO equipe) {
        return ResponseEntity.ok(equipeService.saveEquipe(equipe));
    }

    @GetMapping()
    @Operation(
        summary = "Encontrar todas Equipes"
    )
    public ResponseEntity<List<Equipe>> findAllEquipes() {
        return ResponseEntity.ok(equipeService.findAllEquipes());
    }

    @GetMapping("/membros/{nomeEquipe}")
    @Operation(
        summary = "Encontrar todos membros da Equipe"
    )
    public ResponseEntity<List<Funcionario>> findAllMembros(@PathVariable("nomeEquipe") String nomeEquipe) {
        return ResponseEntity.ok(equipeService.findAllMembros(nomeEquipe));
    }

    @GetMapping("/{nomeEquipe}")
    @Operation(
        summary = "Encontrar Equipe pelo nome"
    )
    public ResponseEntity<Equipe> findByNome(@PathVariable("nomeEquipe") String nomeEquipe) {
        return ResponseEntity.ok(equipeService.findByNome(nomeEquipe));
    }
    
    // UPDATE
    @PatchMapping("/{nomeEquipe}")
    @Operation(
        summary = "Atualizar Equipe"
    )
    public ResponseEntity<Equipe> updateEquipeByNome(@PathVariable("nomeEquipe") String nomeEquipe, @RequestBody EquipeDTO equipeAtualizada) {
        return ResponseEntity.ok(equipeService.updateEquipeByNome(nomeEquipe, equipeAtualizada));
    }

    @PatchMapping("/adicionar/{nomeEquipe}/{cpfFuncionario}")
    @Operation(
        summary = "Adicionar Funcionário a Equipe"
    )
    public ResponseEntity<Funcionario> adicionarFuncionario(@PathVariable("nomeEquipe") String nomeEquipe, @PathVariable("cpfFuncionario") String cpfFuncionario) {
        return ResponseEntity.ok(equipeService.adicionarFuncionario(nomeEquipe, cpfFuncionario));
    }

    @PatchMapping("/remover/{nomeEquipe}/{cpfFuncionario}")
    @Operation(
        summary = "Remover Funcionário da Equipe"
    )
    public ResponseEntity<Funcionario> removerFuncionario(@PathVariable("nomeEquipe") String nomeEquipe, @PathVariable("cpfFuncionario") String cpfFuncionario) {
        return ResponseEntity.ok(equipeService.removerFuncionario(nomeEquipe, cpfFuncionario));
    }
    
    // DELETE
    @DeleteMapping("/{nomeEquipe}")
    @Operation(
        summary = "Deletar Equipe"
    )
    public ResponseEntity<Equipe> deleteEquipeByNome(@PathVariable("nomeEquipe") String nomeEquipe) {
        return ResponseEntity.ok(equipeService.deleteEquipeByNome(nomeEquipe));
    }
}
