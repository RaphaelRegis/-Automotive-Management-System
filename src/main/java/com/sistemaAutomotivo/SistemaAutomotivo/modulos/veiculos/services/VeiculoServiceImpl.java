package com.sistemaAutomotivo.SistemaAutomotivo.modulos.veiculos.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemaAutomotivo.SistemaAutomotivo.modulos.clientes.entities.Cliente;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.clientes.repositories.ClienteRepository;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.veiculos.dto.VeiculoDTO;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.veiculos.entities.Veiculo;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.veiculos.repositories.VeiculoRepository;

@Service
public class VeiculoServiceImpl implements VeiculoService {
    
    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private ClienteRepository clienteRepository;
    
    @Override
    public Veiculo saveVeiculo(VeiculoDTO veiculoDTO) {
        // colocar if existsByCPF
        return veiculoRepository.save(DTOtoVeiculo(veiculoDTO));
    }

    @Override
    public List<Veiculo> findAllVeiculos() {
       // colocar if isEmpty
        return veiculoRepository.findAll();
    }

    @Override
    public Veiculo findById(Integer id) {
        return veiculoRepository.findById(id).get();
    }

    @Override
    public Veiculo updateById(Integer id, VeiculoDTO veiculoDTO) {
        // pega o veiculo existente com base no id
        Veiculo veiculoExistente = findById(id);
        
        // constroi o veiculo atualizado
        Veiculo veiculoAtualizado = DTOtoVeiculo(veiculoDTO);
        veiculoAtualizado.setId_veiculo(veiculoExistente.getId_veiculo());

        // copia as propriedades do veiculo atualizado para o existente
        BeanUtils.copyProperties(veiculoAtualizado, veiculoExistente);

        //salva e retorna novamente o veiculo existente
        return veiculoRepository.save(veiculoExistente);
    }

    @Override
    public Veiculo deleteById(Integer id) {
        Veiculo veiculoExcluido = veiculoRepository.findById(id).get();

        veiculoRepository.delete(veiculoExcluido);

        return veiculoExcluido;
    }

    // metodos auxiliares
    Veiculo DTOtoVeiculo(VeiculoDTO veiculoDTO) {

        Cliente cliente = clienteRepository.findById(veiculoDTO.idCliente()).get();

        Veiculo veiculo = new Veiculo(
            null,
            veiculoDTO.descricao(),
            veiculoDTO.placa(),
            veiculoDTO.ano(),
            cliente);

        return veiculo;
    }




}
