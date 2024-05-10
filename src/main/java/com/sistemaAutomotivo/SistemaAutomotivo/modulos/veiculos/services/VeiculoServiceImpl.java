package com.sistemaAutomotivo.SistemaAutomotivo.modulos.veiculos.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemaAutomotivo.SistemaAutomotivo.modulos.clientes.entities.Cliente;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.clientes.services.ClienteService;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.veiculos.dto.VeiculoDTO;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.veiculos.entities.Veiculo;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.veiculos.repositories.VeiculoRepository;

@Service
public class VeiculoServiceImpl implements VeiculoService {
    
    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private ClienteService clienteService;
    
    @Override
    public Veiculo saveVeiculo(VeiculoDTO veiculoDTO) {
        
        if (veiculoRepository.existsByPlaca(veiculoDTO.placa())) {
            throw new RuntimeException("Placa de veículo já cadastrada!");
        }

        return veiculoRepository.save(DTOtoVeiculo(veiculoDTO));
    }

    @Override
    public List<Veiculo> findAllVeiculos() {

        if (veiculoRepository.findAll().isEmpty()) {
            throw new RuntimeException("Nenhum veículo encontrado!");
        }

        return veiculoRepository.findAll();
    }

    @Override
    public Veiculo findByPlaca(String placa) {
        return veiculoRepository.findByPlaca(placa)
        .orElseThrow(() -> new RuntimeException("Nenhum veículo com essa placa encontrado!"));
    }

    @Override
    public Veiculo updateByPlaca(String placa, VeiculoDTO veiculoDTO) {
        // pega o veiculo existente com base na placa
        Veiculo veiculoExistente = findByPlaca(placa);
        
        // constroi o veiculo atualizado
        veiculoExistente.setDescricao(veiculoDTO.descricao());
        veiculoExistente.setPlaca(veiculoDTO.placa());
        veiculoExistente.setAno(veiculoDTO.ano());

        if (!(veiculoDTO.cpfCnpjCliente().equals(veiculoExistente.getCliente().getCpfCnpj()))) {
            veiculoExistente.setCliente(clienteService.findByCpfCnpj(veiculoDTO.cpfCnpjCliente()));
        }

        //salva e retorna novamente o veiculo existente
        return veiculoRepository.save(veiculoExistente);
    }

    @Override
    public Veiculo deleteByPlaca(String placa) {
        Veiculo veiculoExcluido = findByPlaca(placa);

        veiculoRepository.delete(veiculoExcluido);

        return veiculoExcluido;
    }

    // metodos auxiliares
    Veiculo DTOtoVeiculo(VeiculoDTO veiculoDTO) {

        Cliente cliente = clienteService.findByCpfCnpj(veiculoDTO.cpfCnpjCliente());

        Veiculo veiculo = new Veiculo(
            null,
            veiculoDTO.descricao(),
            veiculoDTO.placa(),
            veiculoDTO.ano(),
            cliente);

        return veiculo;
    }

}
