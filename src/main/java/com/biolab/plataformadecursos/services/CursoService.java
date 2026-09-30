package com.biolab.plataformadecursos.services;

import com.biolab.plataformadecursos.DTOs.CursoRequest;
import com.biolab.plataformadecursos.DTOs.CursoResponse;
import com.biolab.plataformadecursos.entities.Curso;
import com.biolab.plataformadecursos.repositories.AlunoRepository;
import com.biolab.plataformadecursos.repositories.CursoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {
    private final AlunoRepository alunoRepository;
    private final CursoRepository cursoRepository;

    public CursoService(AlunoRepository alunoRepository, CursoRepository cursoRepository) {
        this.alunoRepository = alunoRepository;
        this.cursoRepository = cursoRepository;
    }



    //    POST
    public String criarCurso(CursoRequest request) {
        Curso curso = new Curso();
        curso.setNome(curso.getNome());
        curso.setCargaHoraria(curso.getCargaHoraria());

        cursoRepository.save(curso);
        return "Curso Cadastrado com Sucesso!";
    }



    //    GET
    public List<CursoResponse> mostrarCurso() {
        return cursoRepository.findAll().stream().map(
                curso -> new CursoResponse(
                        curso.getId(), curso.getNome(), curso.getCargaHoraria()
                )
        ).toList();
    }

    public CursoResponse buscarID(long id){
        Optional<Curso> curso = cursoRepository.findById(id);
        CursoResponse cursoResponse = new CursoResponse();
        cursoResponse.setNome(curso.get().getNome());
        cursoResponse.setCargaHoraria(curso.get().getCargaHoraria());
        cursoResponse.setId(curso.get().getId());
        return cursoResponse;
    }



    //    PUT
    public String alterarCurso(long id, CursoRequest request) {
        Curso curso = cursoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Erro: Curso não foi encontrado no sistema!"));

        curso.setNome(request.getNome());
        curso.setCargaHoraria(request.getCargaHoraria());

        cursoRepository.save(curso);
        return "Curso editada com sucesso!";
    }



    //    DELETE
    public String deletarCurso(long id) {
        Optional<Curso> curso = cursoRepository.findById(id);

        if (curso.isEmpty()) {
            return "Curso não existente";
        } else {
            cursoRepository.deleteById(id);
            return "Curso removido com sucesso!";
        }
    }

}
