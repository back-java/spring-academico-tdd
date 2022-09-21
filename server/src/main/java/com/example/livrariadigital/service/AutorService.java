package com.example.livrariadigital.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.livrariadigital.dto.AutorDto;
import com.example.livrariadigital.model.Autor;
import com.example.livrariadigital.repository.AutorRepository;
import com.example.livrariadigital.service.exception.EntityNotFoundException;

@Service
public class AutorService {

    @Autowired
    private AutorRepository repository;

    @Transactional(readOnly = true)
    public List<AutorDto> findAll() {
        List<Autor> allAutors = repository.findAll();
        return allAutors.stream().map(i -> new AutorDto(i)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public AutorDto findById(Long id) {

        Autor autor = repository.findById(id).orElseThrow(
            () -> new EntityNotFoundException("Id '" + id + "' not found")
        );

        return new AutorDto(autor);
    }
    
}
