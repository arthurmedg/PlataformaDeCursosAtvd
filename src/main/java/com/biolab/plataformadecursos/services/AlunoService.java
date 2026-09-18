package com.biolab.plataformadecursos.services;

import com.biolab.plataformadecursos.DTOs.AlunoRequest;
import com.biolab.plataformadecursos.DTOs.AlunoResponse;
import com.biolab.plataformadecursos.entities.Aluno;
import com.biolab.plataformadecursos.entities.Curso;
import com.biolab.plataformadecursos.repositories.AlunoRepository;
import com.biolab.plataformadecursos.repositories.CursoRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;
    private final CursoRepository cursoRepository;

    public AlunoService(AlunoRepository alunoRepository, CursoRepository cursoRepository) {
        this.alunoRepository = alunoRepository;
        this.cursoRepository = cursoRepository;
    }



    //  criar aluno
    public String criarAluno(AlunoRequest request) {
        Aluno aluno = new Aluno();
        aluno.setNome(request.getNome());
        aluno.setEmail(request.getEmail());

        alunoRepository.save(aluno);
        return "Aluno Cadastrado com Sucesso!";
    }

    //  mostrar aluno
    public AlunoResponse buscarAlunoId(long id) {
        Aluno aluno = alunoRepository.findById(id).orElseThrow();
        AlunoResponse response = new AlunoResponse();
        response.setId(aluno.getId());
        response.setNome(aluno.getNome());
        return response;
    }


    //  editar aluno
    public String alterarAluno(long id, AlunoRequest request) {
        Aluno aluno = alunoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Erro: Aluno não foi encontrado no sistema!"));
        Curso curso = cursoRepository.getReferenceById(request.getIdCurso());
        aluno.setNome(request.getNome());
        aluno.setEmail(request.getEmail());
        aluno.setCursos(new HashSet<>());
        aluno.getCursos().add(curso);

        alunoRepository.save(aluno);
        return "Aluno editado com sucesso!";
    }


    //  remover aluno
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
