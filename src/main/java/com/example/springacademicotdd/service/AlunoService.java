package com.example.springacademicotdd.service;

import org.springframework.stereotype.Service;

@Service
public class AlunoService {
    public Integer buscarPelaMatricula(Integer matricula) {
        if(matricula == 1)
            return 1;
        return 0;
    }
}
