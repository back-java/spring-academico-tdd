package com.example.springacademicotdd.repository;

import com.example.springacademicotdd.model.Aluno;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class AlunoRepository {
    private static Map<Long, Aluno> alunos = new HashMap<Long, Aluno>();

    static {
        Aluno aluno = new Aluno(1L, "Robson Leal", "034488808008", "Alvorada");

        alunos.put(aluno.getId(), aluno);
    }

    public Optional<Aluno> findById(Long id) {
        return Optional.of(alunos.get(id));
    }
}
