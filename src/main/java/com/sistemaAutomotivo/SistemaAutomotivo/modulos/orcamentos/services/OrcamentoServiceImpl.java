package com.sistemaAutomotivo.SistemaAutomotivo.modulos.orcamentos.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemaAutomotivo.SistemaAutomotivo.modulos.equipes.entities.Equipe;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.equipes.repositories.EquipeRepository;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.funcionarios.entities.Funcionario;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.funcionarios.repositories.FuncionarioRepository;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.orcamentos.dto.OrcamentoDTO;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.orcamentos.entities.Orcamento;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.produtos.entities.Produto;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.produtos.repositories.ProdutoRepository;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.relacionamentos.dto.ProdutoOrcamentoDTO;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.relacionamentos.dto.ServicoOrcamentoDTO;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.relacionamentos.entities.ProdutoOrcamento;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.relacionamentos.entities.ServicoOrcamento;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.relacionamentos.repositories.ProdutoOrcamentoRepository;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.relacionamentos.repositories.ServicoOrcamentoRepository;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.orcamentos.repositories.OrcamentoRepository;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.servicos.entities.Servico;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.servicos.repositories.ServicoRepository;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.veiculos.entities.Veiculo;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.veiculos.repositories.VeiculoRepository;

@Service
public class OrcamentoServiceImpl implements OrcamentoService {

    @Autowired
    private OrcamentoRepository orcamentoRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private ServicoRepository servicoRepository;

    @Autowired
    private ServicoOrcamentoRepository servicoOrcamentoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ProdutoOrcamentoRepository produtoOrcamentoRepository;

    @Autowired
    private EquipeRepository equipeRepository;

    // CREATE
    @Override
    public Orcamento saveOrcamento(OrcamentoDTO orcamentoDTO) {
        return orcamentoRepository.save(DTOtoOrcamento(orcamentoDTO));
    }

    // READ
    @Override
    public List<Orcamento> findAllOrcamentos() {
        return orcamentoRepository.findAll();
    }

    @Override
    public Orcamento findById(Integer id) {
        return orcamentoRepository.findById(id).get();
    }

    @Override
    public String verifyTotalOrcamento(Integer idOrcamento) {

        Orcamento orcamento = findById(idOrcamento);
        Double valor = 0.0;

        for(ProdutoOrcamento produtoOrcamento: orcamento.getProdutos_orcamentos()) {
            valor += produtoOrcamento.getValor();
        }

        for(ServicoOrcamento servicoOrcamento: orcamento.getServicos_orcamentos()) {
            valor += servicoOrcamento.getValor();
        }

        return "Valor total do orçamento: R$ " + valor;
    }

    // UPDATE
    @Override
    public Orcamento updateById(Integer id, OrcamentoDTO orcamentoDTO) {
        // pega o orcamento existente com base no id
        Orcamento orcamentoExistente = findById(id);

        // constroi o orcamento atualizado
        Orcamento orcamentoAtualizado = DTOtoOrcamento(orcamentoDTO);
        orcamentoAtualizado.setId_orcamento(orcamentoExistente.getId_orcamento());

        // copia as propriedades do orcamento atualizado para o existente
        BeanUtils.copyProperties(orcamentoAtualizado, orcamentoExistente);

        // salva e retorna novamente o orcamento existente
        return orcamentoRepository.save(orcamentoExistente);
    }

    @Override
    public ProdutoOrcamento orcarProduto(ProdutoOrcamentoDTO produtoOrcamentoDTO) {
        Produto produto = produtoRepository.findById(produtoOrcamentoDTO.idProduto()).get();
        Orcamento orcamento = findById(produtoOrcamentoDTO.idOrcamento());

        Double valor = produto.getValor() * produtoOrcamentoDTO.qtd();

        ProdutoOrcamento produtoOrcamento = new ProdutoOrcamento(
                null,
                produto,
                orcamento,
                produtoOrcamentoDTO.qtd(),
                valor,
                produtoOrcamentoDTO.observacao());

        return produtoOrcamentoRepository.save(produtoOrcamento);
    }

    @Override
    public ServicoOrcamento orcarServico(ServicoOrcamentoDTO servicoOrcamentoDTO) {
        Servico servico = servicoRepository.findById(servicoOrcamentoDTO.idServico()).get();
        Orcamento orcamento = findById(servicoOrcamentoDTO.idOrcamento());
        Equipe equipe = equipeRepository.findById(servicoOrcamentoDTO.idEquipe()).get();

        ServicoOrcamento servicoOrcamento = new ServicoOrcamento(
                null,
                servico,
                orcamento,
                equipe,
                servicoOrcamentoDTO.observacao(),
                servico.getValor());

        return servicoOrcamentoRepository.save(servicoOrcamento);
    }

    // DELETE
    @Override
    public Orcamento deleteById(Integer id) {
        Orcamento orcamentoExcluido = orcamentoRepository.findById(id).get();
        orcamentoRepository.delete(orcamentoExcluido);
        return orcamentoExcluido;
    }

    // metodos auxiliares
    public Orcamento DTOtoOrcamento(OrcamentoDTO orcamentoDTO) {

        Funcionario funcionario = funcionarioRepository.findById(orcamentoDTO.idResponsavel()).get();
        Veiculo veiculo = veiculoRepository.findById(orcamentoDTO.idVeiculo()).get();

        Orcamento orcamento = new Orcamento(
                null,
                orcamentoDTO.data(),
                orcamentoDTO.status(),
                //0,
                funcionario,
                veiculo,
                new ArrayList<>(),
                new ArrayList<>());

        return orcamento;
    }

}
