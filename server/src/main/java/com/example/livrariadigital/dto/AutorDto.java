package com.example.livrariadigital.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.example.livrariadigital.model.Autor;
import com.example.livrariadigital.model.Livro;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class AutorDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long codAutor;
    private String nome;
    private String descricao;

    private List<LivroDto> livros = new ArrayList<>();

    public AutorDto(Long codAutor, String nome, String descricao) {
        this.codAutor = codAutor;
        this.nome = nome;
        this.descricao = descricao;
    }

    public AutorDto(Autor autor) {
        codAutor = autor.getCodAutor();
        nome = autor.getNome();
        descricao = autor.getDescricao();
    }

    public AutorDto(Autor autor, Set<Livro> livros) {
        this(autor);
        livros.forEach(livro -> this.livros.add(new LivroDto(livro)));
    }
}
