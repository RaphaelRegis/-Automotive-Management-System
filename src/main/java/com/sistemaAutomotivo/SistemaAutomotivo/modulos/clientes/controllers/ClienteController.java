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

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping()
    public ResponseEntity<Cliente> saveCliente(@RequestBody ClienteDTO clienteDTO) {
        return ResponseEntity.ok(clienteService.saveCliente(clienteDTO));
    }

    @GetMapping()
    public ResponseEntity<List<Cliente>> findAllClientes() {
        return ResponseEntity.ok(clienteService.findAllClientes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> findClienteById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(clienteService.findById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Cliente> updateClienteById(@PathVariable("id") Integer id, @RequestBody ClienteDTO clienteDTO) {
        return ResponseEntity.ok(clienteService.updateById(id, clienteDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Cliente> deleteClienteById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(clienteService.deleteById(id));
    }

}
