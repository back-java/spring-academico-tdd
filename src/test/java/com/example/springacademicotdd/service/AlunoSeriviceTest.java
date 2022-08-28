package com.example.springacademicotdd.service;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.springacademicotdd.model.Aluno;
import com.example.springacademicotdd.repository.AlunoRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class AlunoSeriviceTest {

    @Autowired
    private AlunoService alunoService;

    @MockBean
    private AlunoRepository alunoRepository;

    @Test
    @DisplayName("Test encontrarAlunoPelaMatricula Success")
    void testBuscarAlunoPelaMatriculaSucesso() {
        Aluno mockAluno = new Aluno(1L, "Robson Leal", "033.255.030-08", "Alvorada");
        Mockito.doReturn(Optional.of(mockAluno)).when(alunoRepository).findById(1L);

        Optional<Aluno> optionalAluno = alunoService.buscarPelaMatricula(1L);

        Assertions.assertTrue(optionalAluno.isPresent(), "Aluno não encontrado!");
        Assertions.assertSame(optionalAluno.get(), mockAluno, "Alunos precisam ser os mesmos!");
    }

    @Test
    @DisplayName("Teste encontrarAlunoPelaMatricula NotFound")
    void testBuscarAlunoPelaMatriculaNotFound() {
        Mockito.doReturn(Optional.empty()).when(alunoRepository).findById(1L);

        Optional<Aluno> optionalAluno = alunoService.buscarPelaMatricula(1L);

        Assertions.assertFalse(optionalAluno.isPresent(), "Aluno não deveria ser encontrado!");
    }

}
