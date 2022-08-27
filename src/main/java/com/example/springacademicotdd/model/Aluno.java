package com.example.springacademicotdd.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Aluno {
    private Long id;
    private String nome;
    private String cpf;
    private String cidade;
}
