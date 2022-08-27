package com.example.springacademicotdd.repository;

import com.example.springacademicotdd.model.Aluno;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class AlunoRepository {
    private static Map<Long, Aluno> alunos = new HashMap<Long, Aluno>();

    static {
        Aluno aluno = new Aluno(1L, "Robson Leal", "034488808008", "Alvorada");

        alunos.put(aluno.getId(), aluno);
    }

    public Aluno findById(Long id) {
        return alunos.get(id);
    }
}
