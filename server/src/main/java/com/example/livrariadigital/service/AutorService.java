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

import com.example.livrariadigital.dto.AutorDto;
import com.example.livrariadigital.model.Autor;
import com.example.livrariadigital.repository.AutorRepository;
import com.example.livrariadigital.service.exception.DataIntegrityException;
import com.example.livrariadigital.service.exception.ResourceNotFoundException;

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
            () -> new ResourceNotFoundException("Id '" + id + "' not found")
        );

        return new AutorDto(autor);
    }

    @Transactional
    public AutorDto save(AutorDto autor) {
        Autor novoAutor = new Autor();
        BeanUtils.copyProperties(autor, novoAutor, "cod_autor");
        novoAutor = repository.save(novoAutor);

        return new AutorDto(novoAutor);
    }

    @Transactional
    public AutorDto update(Long id, AutorDto autor) {
        try {
            Autor autorAtualizado = repository.getReferenceById(id);
            autorAtualizado.setNome(autor.getNome());
            autorAtualizado.setDescricao(autor.getDescricao());
            autorAtualizado = repository.save(autorAtualizado);
    
            return new AutorDto(autorAtualizado);
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
