package com.biolab.plataformadecursos.repositories;

import com.biolab.plataformadecursos.entities.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}
