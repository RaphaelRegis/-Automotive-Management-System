package com.sistemaAutomotivo.SistemaAutomotivo.modulos.servicos.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemaAutomotivo.SistemaAutomotivo.modulos.orcamentos.entities.enums.Status;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.servicos.dto.ServicoDTO;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.servicos.entities.Servico;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.servicos.repositories.ServicoRepository;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.relacionamentos.entities.ServicoOrcamento;

@Service
public class ServicoServiceImpl implements ServicoService {

    @Autowired
    private ServicoRepository servicoRepository;

    // CREATE
    @Override
    public Servico saveServico(ServicoDTO servicoDTO) {

        if (servicoRepository.existsByNome(servicoDTO.nome())) {
            throw new RuntimeException("Nome de servico já cadastrado!");
        }

        return servicoRepository.save(DTOtoServico(servicoDTO));
    }

    // READ
    @Override
    public List<Servico> findAllServicos() {

        List<Servico> todosServicos = servicoRepository.findAll();

        if (todosServicos.isEmpty()) {
            throw new RuntimeException("Nenhum serviço encontrado!");
        }

        return todosServicos;
    }

    @Override
    public Servico findByNome(String nome) {

        nome = nome.replace("_", " ");

        return servicoRepository.findByNome(nome)
        .orElseThrow(() -> new RuntimeException("Nenhum servico com esse nome encontrado!"));
    }

    // UPDATE
    @Override
    public Servico updateByNome(String nome, ServicoDTO servicoDTO) {
        // pega o servico existente com base no nome
        Servico servicoExistente = findByNome(nome);

        // constroi o servico atualizado
        servicoExistente.setNome(servicoDTO.nome());
        servicoExistente.setTipoServico(servicoDTO.tipoServico());
        
        // atualiza o valor do servico
        if (servicoDTO.valor() != servicoExistente.getValor()) {
            servicoExistente.setValor(servicoDTO.valor());
            
            // atualiza o valor dos servico_orcamento apenas se o Orcamento associado ainda estiver com Status ABERTO
            for (ServicoOrcamento servicoOrcamento : servicoExistente.getServicos_orcamentos()) {
                if (servicoOrcamento.getOrcamento().getStatus().equals(Status.ABERTO)) {
                    servicoOrcamento.setValor(servicoExistente.getValor());
                }
            }
        }

        // salva e retorna novamente o servico existente
        return servicoRepository.save(servicoExistente);
    }

    // DELETE
    @Override
    public Servico deleteByNome(String nome) {
        Servico servicoExcluido = findByNome(nome);

        servicoRepository.delete(servicoExcluido);

        return servicoExcluido;
    }

    // metodos auxiliares
    Servico DTOtoServico(ServicoDTO servicoDTO) {

        // o servico eh instanciado sem nenhum servico_orcamento
        Servico servico = new Servico(
                null,
                servicoDTO.nome(),
                servicoDTO.valor(),
                servicoDTO.tipoServico(),
                new ArrayList<>());

        return servico;
    }

}
