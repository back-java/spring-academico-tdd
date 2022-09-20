package com.example.livrariadigital.model;

import java.io.Serializable;
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

import com.fasterxml.jackson.annotation.JsonBackReference;

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
@Table(name = "autores")
public class Autor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long codAutor;

    @Column(nullable = false)
    private String nome;

    private String descricao;

    @JsonBackReference
    @ManyToMany
    @JoinTable(
        name               = "autores_livros",
        uniqueConstraints  = @UniqueConstraint(columnNames = {"cod_autor", "cod_livro"}),
        joinColumns        = @JoinColumn(name="cod_autor"),
        inverseJoinColumns = @JoinColumn(name="cod_livro")
    )
    private List<Livro> livros;
}
