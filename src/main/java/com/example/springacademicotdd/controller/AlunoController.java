package com.example.springacademicotdd.controller;

import com.example.springacademicotdd.model.Aluno;
import com.example.springacademicotdd.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> buscarAluno(@PathVariable Long id) {
        Aluno aluno = alunoService.buscarPelaMatricula(id);
        if (aluno == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(aluno);
    }
}
