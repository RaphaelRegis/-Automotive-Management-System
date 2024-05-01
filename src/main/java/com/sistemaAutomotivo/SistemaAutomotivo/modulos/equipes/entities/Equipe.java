package com.sistemaAutomotivo.SistemaAutomotivo.modulos.equipes.entities;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.funcionarios.entities.Funcionario;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.relacionamentos.entities.ServicoOrcamento;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
public class Equipe {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id_equipe;

    @Column(nullable = true, unique = true)
    private String nome;

    private String setor;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "id_responsavel", referencedColumnName = "id_funcionario")
    private Funcionario responsavel;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinTable(
        name = "membro_equipe",	//nome da nova tabela
        joinColumns = @JoinColumn(name = "id_equipe"), //qual coluna da entidade proprietária (a que tem o @JoinTable)
        inverseJoinColumns = @JoinColumn(name = "id_funcionario") //qual coluna da entidade do outro lado do relacionamento
    )
    @JsonIgnore
    private List<Funcionario> membros;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "equipe", cascade = CascadeType.DETACH)
    @JsonIgnore
    private List<ServicoOrcamento> servicos_orcamentos;
}
