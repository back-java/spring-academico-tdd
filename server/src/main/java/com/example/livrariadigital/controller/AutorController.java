package com.example.livrariadigital.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.livrariadigital.dto.AutorDto;
import com.example.livrariadigital.service.AutorService;

@RestController
@RequestMapping(value = "/autores")
public class AutorController {
    
    @Autowired
    private AutorService service;

    @GetMapping
    public ResponseEntity<List<AutorDto>> findAll() {
        List<AutorDto> allAutors = service.findAll();
        return ResponseEntity.ok(allAutors);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AutorDto> findById(@PathVariable Long id) {
        AutorDto autor = service.findById(id);
        return ResponseEntity.ok(autor);
    }

    @PostMapping
    public ResponseEntity<AutorDto> save(@RequestBody AutorDto autor) {
        autor = service.save(autor);

        URI uri = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(autor.getCodAutor())
                    .toUri();

        return ResponseEntity.created(uri).body(autor);
    }
}
