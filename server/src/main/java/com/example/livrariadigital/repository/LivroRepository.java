package com.example.livrariadigital.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.livrariadigital.model.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {}
