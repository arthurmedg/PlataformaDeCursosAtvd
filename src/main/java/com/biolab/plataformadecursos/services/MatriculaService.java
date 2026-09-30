package com.biolab.plataformadecursos.services;

import com.biolab.plataformadecursos.DTOs.AlunoResponse;
import com.biolab.plataformadecursos.DTOs.CursoResponse;
import com.biolab.plataformadecursos.entities.Aluno;
import com.biolab.plataformadecursos.entities.Curso;
import com.biolab.plataformadecursos.repositories.AlunoRepository;
import com.biolab.plataformadecursos.repositories.CursoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MatriculaService {

    private final AlunoRepository alunoRepository;
    private final CursoRepository cursoRepository;

    public MatriculaService(AlunoRepository alunoRepository, CursoRepository cursoRepository) {
        this.alunoRepository = alunoRepository;
        this.cursoRepository = cursoRepository;
    }

    // POST Aluno - Curso
    public String matricular(long alunoId, long cursoId) {
        Optional<Aluno> alunoOpt = alunoRepository.findById(alunoId);
        if (alunoOpt.isEmpty()) {
            return "Erro: Aluno não encontrado!";
        }

        Optional<Curso> cursoOpt = cursoRepository.findById(cursoId);
        if (cursoOpt.isEmpty()) {
            return "Erro: Curso não encontrado!";
        }

        Aluno aluno = alunoOpt.get();
        Curso curso = cursoOpt.get();

        // Evitar duplicatas
        if (aluno.getCursos().contains(curso)) {
            return "Erro: O aluno já está matriculado neste curso!";
        }

        aluno.getCursos().add(curso);
        alunoRepository.save(aluno);

        return "Matrícula realizada com sucesso!";
    }

    // DELETE Aluno - Curso
    public String desmatricular(long alunoId, long cursoId) {
        Optional<Aluno> alunoOpt = alunoRepository.findById(alunoId);
        if (alunoOpt.isEmpty()) {
            return "Erro: Aluno não encontrado!";
        }

        Optional<Curso> cursoOpt = cursoRepository.findById(cursoId);
        if (cursoOpt.isEmpty()) {
            return "Erro: Curso não encontrado!";
        }

        Aluno aluno = alunoOpt.get();
        Curso curso = cursoOpt.get();

        if (!aluno.getCursos().contains(curso)) {
            return "Erro: O aluno não está matriculado neste curso!";
        }

        aluno.getCursos().remove(curso);
        alunoRepository.save(aluno);

        return "Matrícula removida com sucesso!";
    }

    // GET Cursos - Aluno
    public List<CursoResponse> buscarCursosDoAluno(long alunoId) {
        Optional<Aluno> alunoOpt = alunoRepository.findById(alunoId);
        if (alunoOpt.isEmpty()) {
            return new ArrayList<>();
        }

        return alunoOpt.get().getCursos().stream()
                .map(curso -> new CursoResponse(curso.getId(), curso.getNome(), curso.getCargaHoraria()))
                .toList();
    }

    // GET Alunos - Curso
    public List<AlunoResponse> buscarAlunosDoCurso(long cursoId) {
        Optional<Curso> cursoOpt = cursoRepository.findById(cursoId);
        if (cursoOpt.isEmpty()) {
            return new ArrayList<>();
        }

        return cursoOpt.get().getAlunos().stream().map(aluno -> {
            Set<CursoResponse> cursosDto = aluno.getCursos().stream()
                    .map(c -> new CursoResponse(c.getId(), c.getNome(), c.getCargaHoraria()))
                    .collect(Collectors.toSet());

            return new AlunoResponse(aluno.getId(), aluno.getNome(), aluno.getEmail(), cursosDto);
        }).toList();
    }

//    PUT matricula aluno
    public String atualizarMatricula(long alunoId, long cursoAntigoId, long cursoNovoId) {
        Aluno aluno = alunoRepository.findById(alunoId)
                .orElseThrow(() -> new RuntimeException("Erro: Aluno não encontrado!"));

        Curso cursoAntigo = cursoRepository.findById(cursoAntigoId)
                .orElseThrow(() -> new RuntimeException("Erro: Curso antigo não encontrado!"));

        Curso cursoNovo = cursoRepository.findById(cursoNovoId)
                .orElseThrow(() -> new RuntimeException("Erro: Curso novo não encontrado!"));


        if (!aluno.getCursos().contains(cursoAntigo)) {
            return "Erro: O aluno não está matriculado no curso antigo!";
        }

        if (aluno.getCursos().contains(cursoNovo)) {
            return "Erro: O aluno já está matriculado no curso novo!";
        }

        aluno.getCursos().remove(cursoAntigo);
        aluno.getCursos().add(cursoNovo);

        alunoRepository.save(aluno);
        return "Matrícula atualizada com sucesso! Curso alterado.";
    }
}
