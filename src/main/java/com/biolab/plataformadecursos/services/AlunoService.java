package com.biolab.plataformadecursos.services;

import com.biolab.plataformadecursos.DTOs.AlunoRequest;
import com.biolab.plataformadecursos.DTOs.AlunoResponse;
import com.biolab.plataformadecursos.DTOs.CursoRequest;
import com.biolab.plataformadecursos.DTOs.CursoResponse;
import com.biolab.plataformadecursos.entities.Aluno;
import com.biolab.plataformadecursos.entities.Curso;
import com.biolab.plataformadecursos.repositories.AlunoRepository;
import com.biolab.plataformadecursos.repositories.CursoRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;
    private final CursoRepository cursoRepository;

    public AlunoService(AlunoRepository alunoRepository, CursoRepository cursoRepository) {
        this.alunoRepository = alunoRepository;
        this.cursoRepository = cursoRepository;
    }



    //  POST
    public String criarAluno(AlunoRequest request) {
        Aluno aluno = new Aluno();
        aluno.setNome(request.getNome());
        aluno.setEmail(request.getEmail());

        alunoRepository.save(aluno);
        return "Aluno Cadastrado com Sucesso!";
    }

    //  GET
    public List<AlunoResponse> mostrarAlunos() {
        return alunoRepository.findAll().stream().map(aluno -> {
            Set<CursoResponse> cursosDto = aluno.getCursos().stream()
                    .map(c -> new CursoResponse(c.getId(), c.getNome(), c.getCargaHoraria()))
                    .collect(Collectors.toSet());

            return new AlunoResponse(aluno.getId(), aluno.getNome(), aluno.getEmail(), cursosDto);
        }).toList();
    }

    public AlunoResponse buscarID(long id) {
        Optional<Aluno> alunoOpt = alunoRepository.findById(id);
        if (alunoOpt.isEmpty()) {
            return null;
        }

        Aluno aluno = alunoOpt.get();
        Set<CursoResponse> cursosDto = aluno.getCursos().stream()
                .map(c -> new CursoResponse(c.getId(), c.getNome(), c.getCargaHoraria()))
                .collect(Collectors.toSet());

        return new AlunoResponse(aluno.getId(), aluno.getNome(), aluno.getEmail(), cursosDto);
    }


    //  PUT
    public String alterarAluno(long id, AlunoRequest request) {
        Optional<Aluno> alunoOpt = alunoRepository.findById(id);
        if (alunoOpt.isEmpty()) {
            return "Erro: Aluno não foi encontrado no sistema!";
        }

        Aluno aluno = alunoOpt.get();
        aluno.setNome(request.getNome());
        aluno.setEmail(request.getEmail());

        alunoRepository.save(aluno);
        return "Aluno editado com sucesso!";
    }

    //  DELETE
    public String deletarAluno(long id) {
        Optional<Aluno> aluno = alunoRepository.findById(id);

        if (aluno.isEmpty()) {
            return "A aluno não existe";
        }else {
            alunoRepository.deleteById(id);
            return "Aluno removida com sucesso!";
        }
    }

}
