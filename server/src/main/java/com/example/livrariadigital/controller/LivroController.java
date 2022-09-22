package com.example.livrariadigital.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.livrariadigital.dto.LivroDto;
import com.example.livrariadigital.service.LivroService;

@RestController
@RequestMapping(value = "/livros")
public class LivroController {
    
    @Autowired
    private LivroService service;

    @GetMapping
    public ResponseEntity<Page<LivroDto>> findAll(Pageable pageable) {
        Page<LivroDto> allLivros = service.findAll(pageable);
        return ResponseEntity.ok(allLivros);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<LivroDto> findById(@PathVariable Long id) {
        LivroDto livro = service.findById(id);
        return ResponseEntity.ok(livro);
    }

    @PostMapping
    public ResponseEntity<LivroDto> save(@RequestBody LivroDto livro) {
        livro = service.save(livro);

        URI uri = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(livro.getCodLivro())
                    .toUri();

        return ResponseEntity.created(uri).body(livro);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<LivroDto> update(@PathVariable Long id, @RequestBody LivroDto livroDto) {
        livroDto = service.update(id, livroDto);
        return ResponseEntity.ok(livroDto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
