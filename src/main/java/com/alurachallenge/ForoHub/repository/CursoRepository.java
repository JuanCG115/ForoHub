package com.alurachallenge.ForoHub.repository;

import com.alurachallenge.ForoHub.domain.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
}
