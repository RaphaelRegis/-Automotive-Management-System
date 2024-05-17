package com.sistemaAutomotivo.SistemaAutomotivo.modulos.orcamentos.controllers;

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

import com.sistemaAutomotivo.SistemaAutomotivo.modulos.orcamentos.dto.OrcamentoDTO;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.orcamentos.dto.ValorOrcamentoDTO;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.orcamentos.entities.Orcamento;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.orcamentos.services.OrcamentoService;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.relacionamentos.dto.ProdutoOrcamentoDTO;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.relacionamentos.dto.ServicoOrcamentoDTO;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.relacionamentos.entities.ProdutoOrcamento;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.relacionamentos.entities.ServicoOrcamento;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/orcamentos")
@Tag(name = "Módulo Orçamentos")
@SecurityRequirement(name = "bearerAuth")
public class OrcamentoController {

    @Autowired
    private OrcamentoService orcamentoService;

    // CREATE
    @PostMapping()
    @Operation(
        summary = "Salvar Orçamento"
    )
    public ResponseEntity<Orcamento> saveOrcamento(@RequestBody OrcamentoDTO orcamentoDTO) {
        return ResponseEntity.ok(orcamentoService.saveOrcamento(orcamentoDTO));
    }

    // READ
    @GetMapping()
    @Operation(
        summary = "Encontrar todos os Orçamentos"
    )
    public ResponseEntity<List<Orcamento>> findAllOrcamentos() {
        return ResponseEntity.ok(orcamentoService.findAllOrcamentos());
    }

    @GetMapping("/{idOrcamento}")
    @Operation(
        summary = "Encontrar Orçamento por id"
    )
    public ResponseEntity<Orcamento> findOrcamentoById(@PathVariable("idOrcamento") Integer idOrcamento) {
        return ResponseEntity.ok(orcamentoService.findById(idOrcamento));
    }

    @GetMapping("/totalOrcamento/{idOrcamento}")
    @Operation(
        summary = "Verificar total do Orçamento por id"
    )
    public ResponseEntity<ValorOrcamentoDTO> verifyTotalOrcamento(@PathVariable("idOrcamento") Integer idOrcamento) {
        return ResponseEntity.ok(orcamentoService.verifyTotalOrcamento(idOrcamento));
    }

    // UPDATE
    @PatchMapping("/{idOrcamento}")
    @Operation(
        summary = "Atualizar Orçamento"
    )
    public ResponseEntity<Orcamento> updateOrcamentoById(@PathVariable("idOrcamento") Integer idOrcamento, @RequestBody OrcamentoDTO orcamentoDTO) {
        return ResponseEntity.ok(orcamentoService.updateById(idOrcamento, orcamentoDTO));
    }

    @PatchMapping("/orcarProduto")
    @Operation(
        summary = "Orçar Produto para o Orçamento"
    )
    public ResponseEntity<ProdutoOrcamento> orcarProduto(@RequestBody ProdutoOrcamentoDTO produtoOrcamentoDTO) {
        return ResponseEntity.ok(orcamentoService.orcarProduto(produtoOrcamentoDTO));
    }

    @PatchMapping("/orcarServico")
    @Operation(
        summary = "Orçar Serviço para o Orçamento"
    )
    public ResponseEntity<ServicoOrcamento> orcarServico(@RequestBody ServicoOrcamentoDTO servicoOrcamentoDTO) {
        return ResponseEntity.ok(orcamentoService.orcarServico(servicoOrcamentoDTO));
    }

    // DELETE
    @DeleteMapping("/{idOrcamento}")
    @Operation(
        summary = "Deletar Orçamento por id"
    )
    public ResponseEntity<Orcamento> deleteOrcamento(@PathVariable("idOrcamento") Integer idOrcamento) {
        return ResponseEntity.ok(orcamentoService.deleteById(idOrcamento));
    }
    
}
