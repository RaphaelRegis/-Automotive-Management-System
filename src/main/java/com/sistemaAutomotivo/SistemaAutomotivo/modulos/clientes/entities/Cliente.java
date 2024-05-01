package com.sistemaAutomotivo.SistemaAutomotivo.modulos.clientes.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.clientes.entities.enums.TipoCliente;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.veiculos.entities.Veiculo;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id_cliente;

    private String nome;

    @Column(nullable = true, unique = true)
    private String cpf_cnpj;

    private TipoCliente tipo;
    private String telefoneContato;

    @Column(nullable = true, unique = true)
    private String email;

    private String endereco;
    private String cep;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Veiculo> veiculos;
}
