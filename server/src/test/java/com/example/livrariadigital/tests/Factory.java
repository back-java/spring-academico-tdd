package com.example.livrariadigital.tests;

import com.example.livrariadigital.dto.LivroDto;
import com.example.livrariadigital.model.Autor;
import com.example.livrariadigital.model.Livro;

public class Factory {
    public static Livro criarLivro() {
        Livro livro = new Livro(1L, "Livro azul", 1999, true, 25., 120);
        livro.getAutores().add(new Autor(2L, "Autor azul", null));
        return livro;
    }

    public static LivroDto criarLivroDto() {
        Livro livro = criarLivro();
        return new LivroDto(livro, livro.getAutores());
    }
}
