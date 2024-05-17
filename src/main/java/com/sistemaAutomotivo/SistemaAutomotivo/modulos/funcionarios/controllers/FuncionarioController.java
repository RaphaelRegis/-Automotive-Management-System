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

import com.sistemaAutomotivo.SistemaAutomotivo.modulos.equipes.entities.Equipe;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.funcionarios.dto.FuncionarioDTO;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.funcionarios.entities.Funcionario;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.funcionarios.services.FuncionarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/funcionarios")
@Tag(name = "Módulo Funcionários")
@SecurityRequirement(name = "bearerAuth")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    // CREATE
    @PostMapping()
    @Operation(
        summary = "Salvar Funcionário"
    )
    public ResponseEntity<Funcionario> saveFuncionario(@RequestBody FuncionarioDTO funcionarioDTO) {
        return ResponseEntity.ok(funcionarioService.saveFuncionario(funcionarioDTO));
    }

    // READ
    @GetMapping()
    @Operation(
        summary = "Encontrar todos Funcionários"
    )
    public ResponseEntity<List<Funcionario>> findAllFuncionarios(){
        return ResponseEntity.ok(funcionarioService.findAllFuncionarios());
    }

    @GetMapping("/equipes/{cpfFuncionario}")
    @Operation(
        summary = "Encontrar todas equipes do Funcionário"
    )
    public ResponseEntity<List<Equipe>> findAllEquipes(@PathVariable("cpfFuncionario") String cpfFuncionario) {
        return ResponseEntity.ok(funcionarioService.findAllEquipes(cpfFuncionario));
    }

    @GetMapping("/{cpfFuncionario}")
    @Operation(
        summary = "Encontrar Funcionário por CPF"
    )
    public ResponseEntity<Funcionario> findFuncionarioByCpf(@PathVariable("cpfFuncionario") String cpfFuncionario) {
        return ResponseEntity.ok(funcionarioService.findByCpf(cpfFuncionario));
    }

    // UPDATE
    @PatchMapping("/{cpfFuncionario}")
    @Operation(
        summary = "Atualizar Funcionário por CPF"
    )
    public ResponseEntity<Funcionario> updateFuncionarioByCpf(@PathVariable("cpfFuncionario") String cpfFuncionario, @RequestBody FuncionarioDTO funcionarioDTO) {
        return ResponseEntity.ok(funcionarioService.updateByCpf(cpfFuncionario, funcionarioDTO));
    }

    // DELETE
    @DeleteMapping("/{cpfFuncionario}")
    @Operation(
        summary = "Deletar Funcionário por CPF"
    )
    public ResponseEntity<Funcionario> deleteFuncionarioById(@PathVariable("cpfFuncionario") String cpfFuncionario) {
        return ResponseEntity.ok(funcionarioService.deleteByCpf(cpfFuncionario));
    }

}
