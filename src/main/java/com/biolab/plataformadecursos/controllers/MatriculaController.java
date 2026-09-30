package com.biolab.plataformadecursos.controllers;

import com.biolab.plataformadecursos.DTOs.AlunoResponse;
import com.biolab.plataformadecursos.DTOs.CursoResponse;
import com.biolab.plataformadecursos.services.MatriculaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("matricula")
public class MatriculaController {

    private final MatriculaService matriculaService;

    public MatriculaController(MatriculaService matriculaService) {
        this.matriculaService = matriculaService;
    }

    @PostMapping("/aluno/{alunoId}/curso/{cursoId}")
    public ResponseEntity<String> matricular(@PathVariable long alunoId, @PathVariable long cursoId) {
        return ResponseEntity.ok(matriculaService.matricular(alunoId, cursoId));
    }

    @DeleteMapping("/aluno/{alunoId}/curso/{cursoId}")
    public ResponseEntity<String> desmatricular(@PathVariable long alunoId, @PathVariable long cursoId) {
        return ResponseEntity.ok(matriculaService.desmatricular(alunoId, cursoId));
    }

    @GetMapping("/aluno/{alunoId}")
    public ResponseEntity<List<CursoResponse>> buscarCursosDoAluno(@PathVariable long alunoId) {
        return ResponseEntity.ok(matriculaService.buscarCursosDoAluno(alunoId));
    }

    @GetMapping("/curso/{cursoId}")
    public ResponseEntity<List<AlunoResponse>> buscarAlunosDoCurso(@PathVariable long cursoId) {
        return ResponseEntity.ok(matriculaService.buscarAlunosDoCurso(cursoId));
    }
}