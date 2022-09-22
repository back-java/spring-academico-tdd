package com.example.livrariadigital.model;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import javax.persistence.JoinColumn;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "autores")
@NoArgsConstructor @ToString @EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Autor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Getter @Setter private Long codAutor;

    @Column(nullable = false)
    @Getter @Setter private String nome;

    @Column(columnDefinition = "TEXT")
    @Getter @Setter private String descricao;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    @Getter private Instant createdAt;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    @Getter private Instant updatedAt;

    @ManyToMany
    @JoinTable(
        name               = "autores_livros",
        joinColumns        = @JoinColumn(name="cod_autor"),
        inverseJoinColumns = @JoinColumn(name="cod_livro")
    )
    @Getter private Set<Livro> livros = new HashSet<>();

    @PrePersist
    public void prePersist() {
        createdAt = Instant.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = Instant.now();
    }

    public Autor(Long codAutor, String nome, String descricao) {
        this.codAutor = codAutor;
        this.nome = nome;
        this.descricao = descricao;
    }
}
