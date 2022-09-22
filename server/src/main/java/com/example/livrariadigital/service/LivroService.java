package com.example.livrariadigital.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.livrariadigital.dto.AutorDto;
import com.example.livrariadigital.dto.LivroDto;
import com.example.livrariadigital.model.Autor;
import com.example.livrariadigital.model.Livro;
import com.example.livrariadigital.repository.AutorRepository;
import com.example.livrariadigital.repository.LivroRepository;
import com.example.livrariadigital.service.exception.DataIntegrityException;
import com.example.livrariadigital.service.exception.ResourceNotFoundException;

@Service
public class LivroService {

    @Autowired
    private LivroRepository repository;

    @Autowired
    private AutorRepository autorRepository;

    @Transactional(readOnly = true)
    public Page<LivroDto> findAll(Pageable pageable) {
        Page<Livro> allLivros = repository.findAll(pageable);
        return allLivros.map(i -> new LivroDto(i));
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
        //BeanUtils.copyProperties(livro, novoLivro, "cod_livro");
        copyDtoToEntity(livro, novoLivro);
        novoLivro = repository.save(novoLivro);

        return new LivroDto(novoLivro);
    }

    @Transactional
    public LivroDto update(Long id, LivroDto livroDto) {
        try {
            Livro livro = repository.getReferenceById(id);
            copyDtoToEntity(livroDto, livro);
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

    private void copyDtoToEntity(LivroDto dto, Livro entity) {
        entity.setTitulo(dto.getTitulo());
        entity.setAnoLancamento(dto.getAnoLancamento());
        entity.setImportado(dto.getImportado());
        entity.setPreco(dto.getPreco());
        entity.setPrazoEntrega(dto.getPrazoEntrega());

        entity.getAutores().clear();
        for (AutorDto autorDto : dto.getAutores()) {
            Autor autor = autorRepository.getReferenceById(autorDto.getCodAutor());
            entity.getAutores().add(autor);
        }
    }
}
