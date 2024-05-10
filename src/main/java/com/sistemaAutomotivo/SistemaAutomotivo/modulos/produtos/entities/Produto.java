package com.sistemaAutomotivo.SistemaAutomotivo.modulos.produtos.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sistemaAutomotivo.SistemaAutomotivo.modulos.relacionamentos.entities.ProdutoOrcamento;

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
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id_produto;
    
    @Column(nullable = false, unique = true)
    private String nome;
    private String categoria;
    private double valor;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<ProdutoOrcamento> produtoOrcamentos;
}
