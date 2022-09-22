package com.example.livrariadigital.model;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
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
import javax.persistence.UniqueConstraint;

import javax.persistence.JoinColumn;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "livros")
@NoArgsConstructor @ToString @EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Livro implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Getter @Setter private Long codLivro;
    
    @Column(nullable = false)
    @Getter @Setter private String titulo;

    @Column(name = "ano_lancamento", nullable = false)
    @Getter @Setter private Date anoLancamento;
    
    @Column(nullable = false)
    @Getter @Setter private Boolean importado;
    
    @Column(nullable = false)
    @Getter @Setter private Double preco;
    
    @Column(name = "prazo_entrega", nullable = false)
    @Getter @Setter private Integer prazoEntrega;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    @Getter private Instant createdAt;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    @Getter private Instant updatedAt;

    @ManyToMany
    @JoinTable(
        name               = "autores_livros",
        uniqueConstraints  = @UniqueConstraint(columnNames = {"cod_autor", "cod_livro"}),
        joinColumns        = @JoinColumn(name="cod_livro"),
        inverseJoinColumns = @JoinColumn(name="cod_autor")
    )
    @Getter private Set<Autor> autores = new HashSet<>();

    @PrePersist
    public void prePersist() {
        createdAt = Instant.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = Instant.now();
    }

    public Livro(Long codLivro, String titulo, Date anoLancamento, Boolean importado, Double preco,
            Integer prazoEntrega) {
        this.codLivro = codLivro;
        this.titulo = titulo;
        this.anoLancamento = anoLancamento;
        this.importado = importado;
        this.preco = preco;
        this.prazoEntrega = prazoEntrega;
    }
}
