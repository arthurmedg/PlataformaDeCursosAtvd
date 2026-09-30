package com.biolab.plataformadecursos.repositories;

import com.biolab.plataformadecursos.entities.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}
