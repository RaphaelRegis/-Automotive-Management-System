package com.sistemaAutomotivo.SistemaAutomotivo.modulos.veiculos.entities;

import com.sistemaAutomotivo.SistemaAutomotivo.modulos.clientes.entities.Cliente;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id_veiculo;

    private String descricao;

    @Column(nullable = false, unique = true)
    private String placa;

    private int ano;

    // chave estrangeira
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente")
    private Cliente cliente;
}
