package com.example.livrariadigital.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.livrariadigital.model.Autor;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {}
