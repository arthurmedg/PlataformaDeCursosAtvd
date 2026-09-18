package com.biolab.plataformadecursos.repositories;

import com.biolab.plataformadecursos.entities.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
}
