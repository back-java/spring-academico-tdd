package com.example.livrariadigital.dto;

import java.util.Date;

import com.example.livrariadigital.model.Livro;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LivroDto {

    private Long codLivro;
    private String titulo;
    private Date anoLancamento;
    private Boolean importado;
    private Double preco;
    private Integer prazoEntrega;

    public LivroDto(Livro livro) {
        codLivro = livro.getCodLivro();
        titulo = livro.getTitulo();
        anoLancamento = livro.getAnoLancamento();
        importado = livro.getImportado();
        preco = livro.getPreco();
        prazoEntrega = livro.getPrazoEntrega();
    }
}
