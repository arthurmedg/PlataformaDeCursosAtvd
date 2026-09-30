package com.biolab.plataformadecursos.services;

import com.biolab.plataformadecursos.DTOs.AlunoRequest;
import com.biolab.plataformadecursos.DTOs.AlunoResponse;
import com.biolab.plataformadecursos.entities.Aluno;
import com.biolab.plataformadecursos.entities.Curso;
import com.biolab.plataformadecursos.repositories.AlunoRepository;
import com.biolab.plataformadecursos.repositories.CursoRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

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
    public List<AlunoResponse> mostrarAlunos(){
        return alunoRepository.findAll().stream().map(
                aluno -> new AlunoResponse(
                        aluno.getId(), aluno.getNome(), aluno.getEmail())).toList();
    }

    public AlunoResponse buscarID(long id){
        Optional<Aluno> aluno = alunoRepository.findById(id);
        AlunoResponse alunoResponse = new AlunoResponse();
        alunoResponse.setId(aluno.get().getId());
        alunoResponse.setNome(aluno.get().getNome());
        alunoResponse.setEmail(aluno.get().getEmail());

        return alunoResponse;
    }


    //  PUT
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
