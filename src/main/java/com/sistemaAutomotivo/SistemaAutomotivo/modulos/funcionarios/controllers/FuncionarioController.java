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

    @GetMapping("/{idFuncionario}")
    public ResponseEntity<Funcionario> findFuncionarioById(@PathVariable("idFuncionario") Integer idFuncionario) {
        return ResponseEntity.ok(funcionarioService.findById(idFuncionario));
    }

    // UPDATE
    @PatchMapping("/{idFuncionario}")
    public ResponseEntity<Funcionario> updateFuncionarioById(@PathVariable("idFuncionario") Integer idFuncionario, @RequestBody FuncionarioDTO funcionarioDTO) {
        return ResponseEntity.ok(funcionarioService.updateById(idFuncionario, funcionarioDTO));
    }

    // DELETE
    @DeleteMapping("/{idFuncionario}")
    public ResponseEntity<Funcionario> deleteFuncionarioById(@PathVariable("idFuncionario") Integer idFuncionario) {
        return ResponseEntity.ok(funcionarioService.deleteById(idFuncionario));
    }

}
