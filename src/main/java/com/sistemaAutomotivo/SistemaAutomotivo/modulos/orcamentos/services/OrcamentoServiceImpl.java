package com.sistemaAutomotivo.SistemaAutomotivo.modulos.orcamentos.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemaAutomotivo.SistemaAutomotivo.modulos.equipes.entities.Equipe;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.equipes.services.EquipeService;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.funcionarios.entities.Funcionario;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.funcionarios.services.FuncionarioService;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.orcamentos.dto.OrcamentoDTO;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.orcamentos.dto.ValorOrcamentoDTO;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.orcamentos.entities.Orcamento;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.produtos.entities.Produto;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.produtos.services.ProdutoService;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.relacionamentos.dto.ProdutoOrcamentoDTO;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.relacionamentos.dto.ServicoOrcamentoDTO;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.relacionamentos.entities.ProdutoOrcamento;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.relacionamentos.entities.ServicoOrcamento;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.relacionamentos.repositories.ProdutoOrcamentoRepository;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.relacionamentos.repositories.ServicoOrcamentoRepository;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.orcamentos.repositories.OrcamentoRepository;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.servicos.entities.Servico;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.servicos.services.ServicoService;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.veiculos.entities.Veiculo;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.veiculos.services.VeiculoService;

@Service
public class OrcamentoServiceImpl implements OrcamentoService {

    @Autowired
    private OrcamentoRepository orcamentoRepository;

    @Autowired
    private FuncionarioService funcionarioService;

    @Autowired
    private VeiculoService veiculoService;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ServicoService servicoService;

    @Autowired
    private EquipeService equipeService;

    @Autowired
    private ServicoOrcamentoRepository servicoOrcamentoRepository;

    @Autowired
    private ProdutoOrcamentoRepository produtoOrcamentoRepository;

    // CREATE
    @Override
    public Orcamento saveOrcamento(OrcamentoDTO orcamentoDTO) {
        return orcamentoRepository.save(DTOtoOrcamento(orcamentoDTO));
    }

    // READ
    @Override
    public List<Orcamento> findAllOrcamentos() {

        List<Orcamento> todosOrcamentos = orcamentoRepository.findAll();

        if (todosOrcamentos.isEmpty()) {
            throw new RuntimeException("Nenhum orçamento encontrado!");
        }

        return todosOrcamentos;
    }

    @Override
    public Orcamento findById(Integer id) {
        return orcamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nenhum orçamento com esse id encontrado!"));
    }

    @Override
    public ValorOrcamentoDTO verifyTotalOrcamento(Integer idOrcamento) {

        Orcamento orcamento = findById(idOrcamento);
        Double valor = 0.0;

        for (ProdutoOrcamento produtoOrcamento : orcamento.getProdutos_orcamentos()) {
            valor += produtoOrcamento.getValor();
        }

        for (ServicoOrcamento servicoOrcamento : orcamento.getServicos_orcamentos()) {
            valor += servicoOrcamento.getValor();
        }

        return new ValorOrcamentoDTO(orcamento.getId_orcamento(), orcamento.getProdutos_orcamentos(), orcamento.getServicos_orcamentos(), valor);
    }

    // UPDATE
    @Override
    public Orcamento updateById(Integer id, OrcamentoDTO orcamentoDTO) {
        // pega o orcamento existente com base no id
        Orcamento orcamentoExistente = findById(id);

        // constroi o orcamento atualizado
        orcamentoExistente.setData(orcamentoDTO.data());
        orcamentoExistente.setStatus(orcamentoDTO.status());

        if (!(orcamentoDTO.cpfResponsavel().equals(orcamentoExistente.getResponsavel().getCpf()))) {
            orcamentoExistente.setResponsavel(funcionarioService.findByCpf(orcamentoDTO.cpfResponsavel()));
        }
        if (!(orcamentoDTO.placaVeiculo().equals(orcamentoExistente.getVeiculo().getPlaca()))) {
            orcamentoExistente.setVeiculo(veiculoService.findByPlaca(orcamentoDTO.placaVeiculo()));
        }

        // salva e retorna novamente o orcamento existente
        return orcamentoRepository.save(orcamentoExistente);
    }

    @Override
    public ProdutoOrcamento orcarProduto(ProdutoOrcamentoDTO produtoOrcamentoDTO) {

        Produto produto = produtoService.findByNome(produtoOrcamentoDTO.nomeProduto());

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

        Servico servico = servicoService.findByNome(servicoOrcamentoDTO.nomeServico());

        Orcamento orcamento = findById(servicoOrcamentoDTO.idOrcamento());

        Equipe equipe = equipeService.findByNome(servicoOrcamentoDTO.nomeEquipe());

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
        Orcamento orcamentoExcluido = findById(id);
        orcamentoRepository.delete(orcamentoExcluido);
        return orcamentoExcluido;
    }

    // metodos auxiliares
    public Orcamento DTOtoOrcamento(OrcamentoDTO orcamentoDTO) {

        Funcionario funcionario = funcionarioService.findByCpf(orcamentoDTO.cpfResponsavel());
        Veiculo veiculo = veiculoService.findByPlaca(orcamentoDTO.placaVeiculo());

        Orcamento orcamento = new Orcamento(
                null,
                orcamentoDTO.data(),
                orcamentoDTO.status(),
                funcionario,
                veiculo,
                new ArrayList<>(),
                new ArrayList<>());

        return orcamento;
    }

}
