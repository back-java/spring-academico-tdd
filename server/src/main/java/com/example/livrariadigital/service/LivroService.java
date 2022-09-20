package com.example.livrariadigital.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.livrariadigital.model.Livro;
import com.example.livrariadigital.repository.LivroRepository;
import com.example.livrariadigital.service.exception.EntityNotFoundException;

@Service
public class LivroService {

    @Autowired
    private LivroRepository repository;

    public List<Livro> findAll() {
        return repository.findAll();
    }

    public Livro findById(Long id) {
        return repository.findById(id).orElseThrow(
            () -> new EntityNotFoundException("Id '" + id + "' not found")
        );
    }
}
