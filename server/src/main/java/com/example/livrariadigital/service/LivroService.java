package com.example.livrariadigital.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.livrariadigital.model.Livro;
import com.example.livrariadigital.repository.LivroRepository;
import com.example.livrariadigital.service.exception.EntityNotFoundException;

@Service
public class LivroService {

    @Autowired
    private LivroRepository repository;

    @Transactional(readOnly = true)
    public List<Livro> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Livro findById(Long id) {
        return repository.findById(id).orElseThrow(
            () -> new EntityNotFoundException("Id '" + id + "' not found")
        );
    }
}
