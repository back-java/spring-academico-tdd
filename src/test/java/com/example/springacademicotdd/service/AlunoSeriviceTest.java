package com.example.springacademicotdd.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class AlunoSeriviceTest {
    @TestConfiguration
    static class AlunoServiceTestConfiguration {
        @Bean
        public AlunoService alunoService() {
            return new AlunoService();
        }
    }

    @Autowired
    private AlunoService alunoService;

    @Test
    public void buscarAlunoPelaMatriculaTeste() {
        Assertions.assertEquals(1, alunoService.buscarPelaMatricula(1));
    }

}
