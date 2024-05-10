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

@RestController
@RequestMapping("/api/servicos")
public class ServicoController {

    @Autowired
    private ServicoService servicoService;

    // CREATE
    @PostMapping()
    public ResponseEntity<Servico> saveServico(@RequestBody ServicoDTO servicoDTO) {
        return ResponseEntity.ok(servicoService.saveServico(servicoDTO));
    }

    // READ
    @GetMapping()
    public ResponseEntity<List<Servico>> findAllServicos() {
        return ResponseEntity.ok(servicoService.findAllServicos());
    }

    @GetMapping("/{nome}")
    public ResponseEntity<Servico> findServicoById(@PathVariable("nome") String nome) {
        return ResponseEntity.ok(servicoService.findByNome(nome));
    }

    // UPDATE
    @PatchMapping("/{nome}")
    public ResponseEntity<Servico> updateServicoById(@PathVariable("nome") String nome, @RequestBody ServicoDTO servicoDTO) {
        return ResponseEntity.ok(servicoService.updateByNome(nome, servicoDTO));
    }

    // DELETE
    @DeleteMapping("/{nome}")
    public ResponseEntity<Servico> deleteServicoById(@PathVariable("nome") String nome) {
        return ResponseEntity.ok(servicoService.deleteByNome(nome));
    }

}
