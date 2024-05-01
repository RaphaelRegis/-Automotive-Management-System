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

@RestController
@RequestMapping("/api/equipes")
public class EquipeController {
    
    @Autowired
    private EquipeService equipeService;

    // CREATE
    @PostMapping()
    public ResponseEntity<Equipe> saveEquipe(@RequestBody EquipeDTO equipe) {
        return ResponseEntity.ok(equipeService.saveEquipe(equipe));
    }

    // READ
    @GetMapping("/{idEquipe}")
    public ResponseEntity<Equipe> findById(@PathVariable("idEquipe") Integer idEquipe) {
        return ResponseEntity.ok(equipeService.findById(idEquipe));
    }

    @GetMapping()
    public ResponseEntity<List<Equipe>> findAllEquipes() {
        return ResponseEntity.ok(equipeService.findAllEquipes());
    }

    @GetMapping("/membros/{idEquipe}")
    public ResponseEntity<List<Funcionario>> findAllMembros(@PathVariable("idEquipe") Integer idEquipe) {
        return ResponseEntity.ok(equipeService.findAllMembros(idEquipe));
    }
    
    // UPDATE
    @PatchMapping("/{idEquipe}")
    public ResponseEntity<Equipe> updateEquipeById(@PathVariable("idEquipe") Integer idEquipe, @RequestBody EquipeDTO equipeAtualizada) {
        return ResponseEntity.ok(equipeService.updateEquipeById(idEquipe, equipeAtualizada));
    }

    @PatchMapping("/adicionar/{idEquipe}/{idFuncionario}")
    public ResponseEntity<Funcionario> adicionarFuncionario(@PathVariable("idEquipe") Integer idEquipe, @PathVariable("idFuncionario") Integer idFuncionario) {
        return ResponseEntity.ok(equipeService.adicionarFuncionario(idEquipe, idFuncionario));
    }

    @PatchMapping("/remover/{idEquipe}/{idFuncionario}")
    public ResponseEntity<Funcionario> removerFuncionario(@PathVariable("idEquipe") Integer idEquipe, @PathVariable("idFuncionario") Integer idFuncionario) {
        return ResponseEntity.ok(equipeService.removerFuncionario(idEquipe, idFuncionario));
    }
    
    // DELETE
    @DeleteMapping("/{idEquipe}")
    public ResponseEntity<Equipe> deleteEquipeById(@PathVariable("idEquipe") Integer idEquipe) {
        return ResponseEntity.ok(equipeService.deleteEquipeById(idEquipe));
    }
}
