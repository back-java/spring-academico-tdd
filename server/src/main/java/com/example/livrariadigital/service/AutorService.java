package com.example.livrariadigital.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.livrariadigital.model.Autor;
import com.example.livrariadigital.repository.AutorRepository;
import com.example.livrariadigital.service.exception.EntityNotFoundException;

@Service
public class AutorService {

    @Autowired
    private AutorRepository repository;

    @Transactional(readOnly = true)
    public List<Autor> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Autor findById(Long id) {
        return repository.findById(id).orElseThrow(
            () -> new EntityNotFoundException("Id '" + id + "' not found")
        );
    }
    
}
