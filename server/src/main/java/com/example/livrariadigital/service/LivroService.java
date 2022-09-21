package com.example.livrariadigital.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.livrariadigital.dto.LivroDto;
import com.example.livrariadigital.model.Livro;
import com.example.livrariadigital.repository.LivroRepository;
import com.example.livrariadigital.service.exception.DataIntegrityException;
import com.example.livrariadigital.service.exception.ResourceNotFoundException;

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
            () -> new ResourceNotFoundException("Id '" + id + "' not found")
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

    @Transactional
    public LivroDto update(Long id, LivroDto livroDto) {
        try {
            Livro livro = repository.getReferenceById(id);
            livro.setTitulo(livroDto.getTitulo());
            livro.setAnoLancamento(livroDto.getAnoLancamento());
            livro.setImportado(livroDto.getImportado());
            livro.setPreco(livroDto.getPreco());
            livro.setPrazoEntrega(livroDto.getPrazoEntrega());
            livro = repository.save(livro);
    
            return new LivroDto(livro);
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id '" + id + "' not found");
        }
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Id '" + id + "' not found");
        }
        catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Integrity violation");
        }
    }
}
