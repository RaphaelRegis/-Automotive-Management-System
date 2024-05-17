package com.sistemaAutomotivo.SistemaAutomotivo.modulos.servicos.controllers;


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

import com.sistemaAutomotivo.SistemaAutomotivo.modulos.servicos.dto.ServicoDTO;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.servicos.entities.Servico;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.servicos.services.ServicoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/servicos")
@Tag(name = "Módulo Serviços")
@SecurityRequirement(name = "bearerAuth")
public class ServicoController {

    @Autowired
    private ServicoService servicoService;

    // CREATE
    @PostMapping()
    @Operation(
        summary = "Salvar Serviço"
    )
    public ResponseEntity<Servico> saveServico(@RequestBody ServicoDTO servicoDTO) {
        return ResponseEntity.ok(servicoService.saveServico(servicoDTO));
    }

    // READ
    @GetMapping()
    @Operation(
        summary = "Encontrar todos os Serviços"
    )
    public ResponseEntity<List<Servico>> findAllServicos() {
        return ResponseEntity.ok(servicoService.findAllServicos());
    }

    @GetMapping("/{nome}")
    @Operation(
        summary = "Encontrar Serviço por nome"
    )
    public ResponseEntity<Servico> findServicoByNome(@PathVariable("nome") String nome) {
        return ResponseEntity.ok(servicoService.findByNome(nome));
    }

    // UPDATE
    @PatchMapping("/{nome}")
    @Operation(
        summary = "Atualizar Serviço por nome"
    )
    public ResponseEntity<Servico> updateServicoByNome(@PathVariable("nome") String nome, @RequestBody ServicoDTO servicoDTO) {
        return ResponseEntity.ok(servicoService.updateByNome(nome, servicoDTO));
    }

    // DELETE
    @DeleteMapping("/{nome}")
    @Operation(
        summary = "Deletar Serviço por nome"
    )
    public ResponseEntity<Servico> deleteServicoByNome(@PathVariable("nome") String nome) {
        return ResponseEntity.ok(servicoService.deleteByNome(nome));
    }

}
