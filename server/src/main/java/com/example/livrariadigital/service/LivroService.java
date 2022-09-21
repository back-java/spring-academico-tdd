package com.example.livrariadigital.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.livrariadigital.dto.LivroDto;
import com.example.livrariadigital.model.Livro;
import com.example.livrariadigital.repository.LivroRepository;
import com.example.livrariadigital.service.exception.EntityNotFoundException;

@Service
public class LivroService {

    @Autowired
    private LivroRepository repository;

    @Transactional(readOnly = true)
    public List<LivroDto> findAll() {
        List<Livro> allLivros = repository.findAll();
        return allLivros.stream().map(i -> new LivroDto(i)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public LivroDto findById(Long id) {
        Livro livro = repository.findById(id).orElseThrow(
            () -> new EntityNotFoundException("Id '" + id + "' not found")
        );

        return new LivroDto(livro);
    }

    @Transactional
    public LivroDto save(LivroDto livro) {
        Livro novoLivro = new Livro();
        BeanUtils.copyProperties(livro, novoLivro, "cod_livro");
        novoLivro = repository.save(novoLivro);

        return new LivroDto(novoLivro);
    }
}
