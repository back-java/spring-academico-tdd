package com.example.livrariadigital.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.web.servlet.MockMvc;

import com.example.livrariadigital.dto.LivroDto;
import com.example.livrariadigital.service.LivroService;
import com.example.livrariadigital.tests.Factory;

@WebMvcTest(LivroController.class)
public class LivroControllerTests {
    
    private LivroDto livroDto;
    private PageImpl<LivroDto> page;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LivroService service;

    @BeforeEach
    void setUp() throws Exception {

        livroDto = Factory.criarLivroDto();
        page = new PageImpl<>(List.of(livroDto));

        when(service.findAll(any())).thenReturn(page);
    }

    @Test
    public void findAllDeveRetornarUmPage() throws Exception {
        mockMvc.perform(get("/livros")).andExpect(status().isOk());
    }
}
