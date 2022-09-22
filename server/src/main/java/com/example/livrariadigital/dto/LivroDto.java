package com.example.livrariadigital.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.example.livrariadigital.model.Autor;
import com.example.livrariadigital.model.Livro;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class LivroDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long codLivro;
    private String titulo;
    private Date anoLancamento;
    private Boolean importado;
    private Double preco;
    private Integer prazoEntrega;

    private List<AutorDto> autores = new ArrayList<>();

    public LivroDto(Long codLivro, String titulo, Date anoLancamento, Boolean importado, Double preco,
            Integer prazoEntrega) {
        this.codLivro = codLivro;
        this.titulo = titulo;
        this.anoLancamento = anoLancamento;
        this.importado = importado;
        this.preco = preco;
        this.prazoEntrega = prazoEntrega;
    }

    public LivroDto(Livro livro) {
        codLivro = livro.getCodLivro();
        titulo = livro.getTitulo();
        anoLancamento = livro.getAnoLancamento();
        importado = livro.getImportado();
        preco = livro.getPreco();
        prazoEntrega = livro.getPrazoEntrega();
    }

    public LivroDto(Livro livro, Set<Autor> autores) {
        this(livro);
        autores.forEach(autor -> this.autores.add(new AutorDto(autor)));
    }    
}
