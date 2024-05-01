package com.sistemaAutomotivo.SistemaAutomotivo.modulos.relacionamentos.entities;

import com.sistemaAutomotivo.SistemaAutomotivo.modulos.orcamentos.entities.Orcamento;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.produtos.entities.Produto;

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
public class ProdutoOrcamento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id_produto_orcamento;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "id_produto", referencedColumnName = "id_produto")
    private Produto produto;
    
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "id_orcamento", referencedColumnName = "id_orcamento")
    private Orcamento orcamento;
        
    private int quantidade;
    private double valor;
    private String observacao;
}
