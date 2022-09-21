package com.example.livrariadigital.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import javax.persistence.JoinColumn;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "livros")
public class Livro implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long codLivro;
    
    @Column(nullable = false)
    private String titulo;

    @Column(name = "ano_lancamento", nullable = false)
    private Date anoLancamento;
    
    @Column(nullable = false)
    private Boolean importado;
    
    @Column(nullable = false)
    private Double preco;
    
    @Column(name = "prazo_entrega", nullable = false)
    private Integer prazoEntrega;

    @ManyToMany
    @JoinTable(
        name               = "autores_livros",
        uniqueConstraints  = @UniqueConstraint(columnNames = {"cod_autor", "cod_livro"}),
        joinColumns        = @JoinColumn(name="cod_livro"),
        inverseJoinColumns = @JoinColumn(name="cod_autor")
    )
    private List<Autor> autores;
}
