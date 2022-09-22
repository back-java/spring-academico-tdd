package com.example.livrariadigital.repository;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

import com.example.livrariadigital.model.Livro;
import com.example.livrariadigital.tests.Factory;

@DataJpaTest
public class LivroRepositoryTests {

    long idExistente;
    long idNaoExistente;
    int totalDeLivros;

    @Autowired
    private LivroRepository repository;

    @BeforeEach
    void setUp() throws Exception {
        idExistente = 1L;
        idNaoExistente = 100L;
        totalDeLivros = 5; 
    }

    @Test
    void salvarDeveSalvarNovoValorEhAutoIncrementarIdQuandoIdForNulo() {
        Livro livro = Factory.criarLivro();
        livro.setCodLivro(null);

        livro = repository.save(livro);

        Assertions.assertNotNull(livro.getCodLivro());
        Assertions.assertEquals(totalDeLivros + 1, livro.getCodLivro());
    }

    @Test
    void deletarDeveDetalharUmObjetoQuandoEleExistir() {
        repository.deleteById(idExistente);

        Optional<Livro> optional = repository.findById(idExistente);
        Assertions.assertFalse(optional.isPresent()); 
    }

    @Test
    void deletarDeveLançarUmaExceptionQuandoObjetoNaoExiste() {
        Assertions.assertThrows(EmptyResultDataAccessException.class,
            () -> {
                repository.deleteById(idNaoExistente);
            }
        );
    }
}
