package com.sistemaAutomotivo.SistemaAutomotivo.modulos.orcamentos.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemaAutomotivo.SistemaAutomotivo.modulos.funcionarios.entities.Funcionario;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.funcionarios.repositories.FuncionarioRepository;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.orcamentos.dto.OrcamentoDTO;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.orcamentos.entities.Orcamento;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.orcamentos.repositories.OrcamentoRepository;
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
    
    @Override
    public Orcamento saveOrcamento(OrcamentoDTO orcamentoDTO) {
        return orcamentoRepository.save(DTOtoOrcamento(orcamentoDTO));
    }

    @Override
    public List<Orcamento> findAllOrcamentos() {
        return orcamentoRepository.findAll();
    }

    @Override
    public Orcamento findById(Integer id) {
        return orcamentoRepository.findById(id).get();
    }

    @Override
    public Orcamento updateById(Integer id, OrcamentoDTO orcamentoDTO) {
        // pega o orcamento existente com base no id
        Orcamento orcamentoExistente = findById(id);
        
        // constroi o orcamento atualizado
        Orcamento orcamentoAtualizado = DTOtoOrcamento(orcamentoDTO);
        orcamentoAtualizado.setId_orcamento(orcamentoExistente.getId_orcamento());

        // copia as propriedades do orcamento atualizado para o existente
        BeanUtils.copyProperties(orcamentoAtualizado, orcamentoExistente);

        //salva e retorna novamente o orcamento existente
        return orcamentoRepository.save(orcamentoExistente);
    }

    @Override
    public Orcamento deleteById(Integer id) {
        Orcamento orcamentoExcluido = orcamentoRepository.findById(id).get();
        orcamentoRepository.delete(orcamentoExcluido);
        return orcamentoExcluido;
    }

    // metodos auxiliares
    Orcamento DTOtoOrcamento(OrcamentoDTO orcamentoDTO) {

        Funcionario funcionario = funcionarioRepository.findById(orcamentoDTO.idResponsavel()).get();
        Veiculo veiculo = veiculoRepository.findById(orcamentoDTO.idVeiculo()).get();

        // o orcamento eh instanciado sem nenhum servico_orcamento
        Orcamento orcamento = new Orcamento(
            null,
            orcamentoDTO.data(),
            orcamentoDTO.status(),
            orcamentoDTO.total_orcamento(),
            funcionario,
            veiculo,
            null);

        return orcamento;
    }




}
