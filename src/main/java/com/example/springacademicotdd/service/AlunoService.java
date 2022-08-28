package com.example.springacademicotdd.service;

import com.example.springacademicotdd.model.Aluno;
import com.example.springacademicotdd.repository.AlunoRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    public Optional<Aluno> buscarPelaMatricula(Long matricula) {
        return alunoRepository.findById(matricula);
    }
}
