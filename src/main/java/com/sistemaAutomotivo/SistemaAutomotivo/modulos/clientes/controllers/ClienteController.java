package com.sistemaAutomotivo.SistemaAutomotivo.modulos.clientes.controllers;


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

import com.sistemaAutomotivo.SistemaAutomotivo.modulos.clientes.dto.ClienteDTO;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.clientes.entities.Cliente;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.clientes.services.ClienteService;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.veiculos.entities.Veiculo;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;
    
    // CREATE
    @PostMapping()
    public ResponseEntity<Cliente> saveCliente(@RequestBody ClienteDTO clienteDTO) {
        return ResponseEntity.ok(clienteService.saveCliente(clienteDTO));
    }

    // READ
    @GetMapping()
    public ResponseEntity<List<Cliente>> findAllClientes() {
        return ResponseEntity.ok(clienteService.findAllClientes());
    }

    @GetMapping("/{cpfCnpj}")
    public ResponseEntity<Cliente> findClienteByCpfCnpj(@PathVariable("cpfCnpj") String cpfCnpj) {
        return ResponseEntity.ok(clienteService.findByCpfCnpj(cpfCnpj));
    }

    @GetMapping("/veiculos/{cpfCnpj}")
    public ResponseEntity<List<Veiculo>> findClienteVeiculos(@PathVariable("cpfCnpj") String cpfCnpj) {
        return ResponseEntity.ok(clienteService.findVeiculos(cpfCnpj));
    }

    // UPDATE
    @PatchMapping("/{cpfCnpj}")
    public ResponseEntity<Cliente> updateClienteByCpfCnpj(@PathVariable("cpfCnpj") String cpfCnpj, @RequestBody ClienteDTO clienteDTO) {
        return ResponseEntity.ok(clienteService.updateByCpfCnpj(cpfCnpj, clienteDTO));
    }

    // DELETE
    @DeleteMapping("/{cpfCnpj}")
    public ResponseEntity<Cliente> deleteClienteByCpfCnpj(@PathVariable("cpfCnpj") String cpfCnpj) {
        return ResponseEntity.ok(clienteService.deleteByCpfCnpj(cpfCnpj));
    }
}
