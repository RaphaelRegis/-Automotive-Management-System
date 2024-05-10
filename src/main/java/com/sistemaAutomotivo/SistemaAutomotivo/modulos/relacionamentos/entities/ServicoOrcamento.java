package com.sistemaAutomotivo.SistemaAutomotivo.modulos.relacionamentos.entities;

import com.sistemaAutomotivo.SistemaAutomotivo.modulos.equipes.entities.Equipe;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.orcamentos.entities.Orcamento;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.servicos.entities.Servico;

import jakarta.persistence.CascadeType;
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
public class ServicoOrcamento {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id_servico_orcamento;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "id_servico", referencedColumnName = "id_servico")
    private Servico servico;
    
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "id_orcamento", referencedColumnName = "id_orcamento")
    private Orcamento orcamento;
    
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "id_equipe", referencedColumnName = "id_equipe")
    private Equipe equipe;

    private String observacao;
    private double valor;
}
