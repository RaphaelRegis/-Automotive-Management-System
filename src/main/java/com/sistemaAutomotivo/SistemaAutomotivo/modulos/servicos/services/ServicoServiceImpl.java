package com.sistemaAutomotivo.SistemaAutomotivo.modulos.servicos.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemaAutomotivo.SistemaAutomotivo.modulos.servicos.dto.ServicoDTO;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.servicos.entities.Servico;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.servicos.repositories.ServicoRepository;

@Service
public class ServicoServiceImpl implements ServicoService {
    
    @Autowired
    private ServicoRepository servicoRepository;
    
    @Override
    public Servico saveServico(ServicoDTO servicoDTO) {
        // colocar if existsByCPF
        return servicoRepository.save(DTOtoServico(servicoDTO));
    }

    @Override
    public List<Servico> findAllServicos() {
       // colocar if isEmpty
        return servicoRepository.findAll();
    }

    @Override
    public Servico findById(Integer id) {
        return servicoRepository.findById(id).get();
    }

    @Override
    public Servico updateById(Integer id, ServicoDTO servicoDTO) {
        // pega o servico existente com base no id
        Servico servicoExistente = findById(id);
        
        // constroi o servico atualizado
        Servico servicoAtualizado = DTOtoServico(servicoDTO);
        servicoAtualizado.setId_servico(servicoExistente.getId_servico());

        // copia as propriedades do servico atualizado para o existente
        BeanUtils.copyProperties(servicoAtualizado, servicoExistente);

        //salva e retorna novamente o servico existente
        return servicoRepository.save(servicoExistente);
    }

    @Override
    public Servico deleteById(Integer id) {
        Servico servicoExcluido = servicoRepository.findById(id).get();

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
            null);

        return servico;
    }




}
