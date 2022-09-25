package com.example.livrariadigital.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.livrariadigital.dto.LivroDto;
import com.example.livrariadigital.service.LivroService;
import com.example.livrariadigital.service.exception.DataIntegrityException;
import com.example.livrariadigital.service.exception.ResourceNotFoundException;
import com.example.livrariadigital.tests.Factory;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(LivroController.class)
public class LivroControllerTests {
    
    private LivroDto livroDto;
    private PageImpl<LivroDto> page;
    private Long idExistente;
    private Long idNaoExistente;
    private Long idDependente;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private LivroService service;

    @BeforeEach
    void setUp() throws Exception {

        livroDto = Factory.criarLivroDto();
        page = new PageImpl<>(List.of(livroDto));
        idExistente = 1L;
        idNaoExistente = 2L;
        idDependente = 3L;

        when(service.findAll(any())).thenReturn(page);
        when(service.findById(idExistente)).thenReturn(livroDto);
        when(service.findById(idNaoExistente)).thenThrow(ResourceNotFoundException.class);
        when(service.update(eq(idExistente), any())).thenReturn(livroDto);
        when(service.update(eq(idNaoExistente), any())).thenThrow(ResourceNotFoundException.class);
        when(service.save(any())).thenReturn(livroDto);

        doNothing().when(service).delete(idExistente);
        doThrow(ResourceNotFoundException.class).when(service).delete(idNaoExistente);
        doThrow(DataIntegrityException.class).when(service).delete(idDependente);
    }

    @Test
    public void saveDeveRetornarUmLivroComIdPreenchidoPeloBD() throws Exception {
        String jsonBody = objectMapper.writeValueAsString(livroDto);

        mockMvc.perform(
            post("/livros")
                .content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.codLivro").exists());
    }

    @Test
    public void deleteNaoDeveFazerNadaQuandoExistirOhId() throws Exception {
        mockMvc.perform(
            delete("/livros/{id}", idExistente)
                .accept(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isNoContent());
    }

    @Test
    public void deleteDeveRetornarNotFoundQuandoOhIdNaoExistir() throws Exception {
        mockMvc.perform(
            delete("/livros/{id}", idNaoExistente)
                .accept(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isNotFound());
    }

    @Test
    public void updateDeveRetornarUmProdutoQuandoOhIdExistir() throws Exception {
        String jsonBody = objectMapper.writeValueAsString(livroDto);

        mockMvc.perform(
            put("/livros/{id}", idExistente)
                .content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.codLivro", "$.titulo").exists());
    }

    @Test
    public void updateDeveRetornarNotFoundQuadoIdNaoExistir() throws Exception {
        String jsonBody = objectMapper.writeValueAsString(livroDto);

        mockMvc.perform(
            put("/livros/{id}", idNaoExistente)
                .content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isNotFound());
    }

    @Test
    public void findAllDeveRetornarUmPage() throws Exception {
        mockMvc.perform(
            get("/livros")
                .accept(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isOk());
    }

    @Test
    public void findByIdDeveRetornarUmProdutoQuandoOhIdForExistente() throws Exception {
        mockMvc.perform(
            get("/livros/{id}", idExistente)
                .accept(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.codLivro", "$.titulo").exists());
    }

    @Test
    public void findByIdDeveRetornarNotFoundQuandoOhIdNãoExistir() throws Exception {
        mockMvc.perform(
            get("/livros/{id}", idNaoExistente)
                .accept(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isNotFound());
    }
}
