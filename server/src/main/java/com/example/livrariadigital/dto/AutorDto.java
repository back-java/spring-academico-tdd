package com.example.livrariadigital.dto;

import com.example.livrariadigital.model.Autor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AutorDto {

    private Long codAutor;
    private String nome;
    private String descricao;

    public AutorDto(Autor autor) {
        codAutor = autor.getCodAutor();
        nome = autor.getNome();
        descricao = autor.getDescricao();
    };
}
