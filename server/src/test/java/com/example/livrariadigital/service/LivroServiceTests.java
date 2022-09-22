package com.example.livrariadigital.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.livrariadigital.dto.LivroDto;
import com.example.livrariadigital.model.Livro;
import com.example.livrariadigital.repository.LivroRepository;
import com.example.livrariadigital.service.exception.DataIntegrityException;
import com.example.livrariadigital.service.exception.ResourceNotFoundException;
import com.example.livrariadigital.tests.Factory;

@ExtendWith(SpringExtension.class)
public class LivroServiceTests {
    
    private long idExistente;
    private long idNaoExistente;
    private long idComDependecias;
    private PageImpl<Livro> page;
    private Livro livro;
    private Livro livroIdNulo;

    @InjectMocks
    private LivroService service;

    @Mock
    private LivroRepository repository;

    @BeforeEach
    void setUp() throws Exception {
        idExistente = 1L;
        idNaoExistente = 100L;
        idComDependecias = 4L;
        livro = Factory.criarLivro();
        livroIdNulo = Factory.criarLivroSemId();
        page = new PageImpl<>(List.of(livro));

        when(repository.findAll((Pageable)ArgumentMatchers.any())).thenReturn(page);
        when(repository.save((Livro)ArgumentMatchers.any())).thenReturn(livro);
        when(repository.findById(idExistente)).thenReturn(Optional.of(livro));
        when(repository.findById(idNaoExistente)).thenReturn(Optional.empty());

        doNothing().when(repository).deleteById(idExistente);
        doThrow(EmptyResultDataAccessException.class).when(repository).deleteById(idNaoExistente);
        doThrow(DataIntegrityViolationException.class).when(repository).deleteById(idComDependecias);
    }

    @Test
    public void saveDeveSalvarUmNovoLivroEatribuirUmIdQuandoEstePossuirUmIdNulo() {
        LivroDto livroDto = service.save(new LivroDto(livroIdNulo));

        assertNotNull(livroDto.getCodLivro());
        verify(repository, times(1)).save(livroIdNulo);
    }

    @Test
    public void deleteNaoDeveFazerNadaQuandoUmIdExistir() {
        Assertions.assertDoesNotThrow(
            () -> service.delete(idExistente)
        );

        verify(repository, times(1)).deleteById(idExistente);
    }

    @Test
    public void deleteDeveLancarRosourceNotFoundExceptionQuandoIdNaoExistir() {
        Assertions.assertThrows(
            ResourceNotFoundException.class,
            () -> service.delete(idNaoExistente)
        );

        verify(repository).deleteById(idNaoExistente);
    }

    @Test
    public void deleteDeveLancarDataIntegrityExceptionQuandoDelecaoCausarFalhaDeIntegridadeNoBanco() {
        Assertions.assertThrows(
            DataIntegrityException.class,
            () -> service.delete(idComDependecias)
        );
    }

    @Test
    public void findAllDeveRetornarUmaPaginacao() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<LivroDto> resultado = service.findAll(pageable);

        Assertions.assertNotNull(resultado);
        verify(repository, times(1)).findAll(pageable);
    }
}
