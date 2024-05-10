package com.sistemaAutomotivo.SistemaAutomotivo.modulos.servicos.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.relacionamentos.entities.ServicoOrcamento;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.servicos.entities.enums.TipoServico;

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
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id_servico;

    @Column(nullable = false, unique = true)
    private String nome;
    private double valor;
    private TipoServico tipoServico;

    @OneToMany(mappedBy = "servico", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<ServicoOrcamento> servicos_orcamentos;

}
