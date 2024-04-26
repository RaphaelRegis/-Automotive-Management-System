package com.sistemaAutomotivo.SistemaAutomotivo.modulos.funcionarios.controllers;

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

import com.sistemaAutomotivo.SistemaAutomotivo.modulos.funcionarios.dto.FuncionarioDTO;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.funcionarios.entities.Funcionario;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.funcionarios.services.FuncionarioService;

@RestController
@RequestMapping("/api/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    // CREATE
    @PostMapping()
    public ResponseEntity<Funcionario> saveFuncionario(@RequestBody FuncionarioDTO funcionarioDTO) {
        return ResponseEntity.ok(funcionarioService.saveFuncionario(funcionarioDTO));
    }

    // READ
    @GetMapping()
    public ResponseEntity<List<Funcionario>> findAllFuncionarios() {
        return ResponseEntity.ok(funcionarioService.findAllFuncionarios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Funcionario> findFuncionarioById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(funcionarioService.findById(id));
    }

    // UPDATE
    @PatchMapping("/{id}")
    public ResponseEntity<Funcionario> updateFuncionarioById(@PathVariable("id") Integer id, @RequestBody FuncionarioDTO funcionarioDTO) {
        return ResponseEntity.ok(funcionarioService.updateById(id, funcionarioDTO));
    }

    @PatchMapping("/liderarEquipe/{idFuncionario}/{idEquipe}")
    public ResponseEntity<Funcionario> definirLiderancaDeEquipe(@PathVariable("idFuncionario") Integer idFuncionario, @PathVariable("idEquipe") Integer idEquipe) {
        return ResponseEntity.ok(funcionarioService.definirLiderEquipe(idFuncionario, idEquipe));
    }

    @PatchMapping("/integrarEquipe/{idFuncionario}/{idEquipe}")
    public ResponseEntity<Funcionario> integrarEquipe(@PathVariable("idFuncionario") Integer idFuncionario, @PathVariable("idEquipe") Integer idEquipe) {
       return ResponseEntity.ok(funcionarioService.integrarEquipe(idFuncionario, idEquipe));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Funcionario> deleteFuncionarioById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(funcionarioService.deleteById(id));
    }

}
