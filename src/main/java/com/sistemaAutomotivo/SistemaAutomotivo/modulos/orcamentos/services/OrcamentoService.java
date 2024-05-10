package com.sistemaAutomotivo.SistemaAutomotivo.modulos.orcamentos.services;

import java.util.List;

import com.sistemaAutomotivo.SistemaAutomotivo.modulos.orcamentos.dto.OrcamentoDTO;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.orcamentos.dto.ValorOrcamentoDTO;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.orcamentos.entities.Orcamento;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.relacionamentos.dto.ProdutoOrcamentoDTO;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.relacionamentos.dto.ServicoOrcamentoDTO;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.relacionamentos.entities.ProdutoOrcamento;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.relacionamentos.entities.ServicoOrcamento;

public interface OrcamentoService {
    
    // CREATE
    Orcamento saveOrcamento(OrcamentoDTO orcamentoDTO);

    // READ
    List<Orcamento> findAllOrcamentos();
    Orcamento findById(Integer id);
    ValorOrcamentoDTO verifyTotalOrcamento(Integer idOrcamento);

    // UPDATE
    Orcamento updateById(Integer id, OrcamentoDTO orcamentoDTO);
    ProdutoOrcamento orcarProduto(ProdutoOrcamentoDTO produtoOrcamentoDTO);
    ServicoOrcamento orcarServico(ServicoOrcamentoDTO servicoOrcamentoDTO);


    // DELETE
    Orcamento deleteById(Integer id);
}
