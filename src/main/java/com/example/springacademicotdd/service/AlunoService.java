package com.example.springacademicotdd.service;

import com.example.springacademicotdd.model.Aluno;
import com.example.springacademicotdd.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    public Aluno buscarPelaMatricula(Long matricula) {
        return alunoRepository.findById(matricula);
    }
}
