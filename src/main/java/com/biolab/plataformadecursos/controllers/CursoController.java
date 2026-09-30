package com.biolab.plataformadecursos.controllers;

import com.biolab.plataformadecursos.DTOs.AlunoResponse;
import com.biolab.plataformadecursos.DTOs.CursoRequest;
import com.biolab.plataformadecursos.DTOs.CursoResponse;
import com.biolab.plataformadecursos.services.CursoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("curso")
public class CursoController {
    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }



    @PostMapping
    public ResponseEntity<?> criarCurso(@RequestBody CursoRequest cursoRequest) {
            return ResponseEntity.ok("Criado com sucesso!" + cursoService.criarCurso(cursoRequest));
    }

    @GetMapping
    public ResponseEntity<?> mostrarCurso() {
        return ResponseEntity.ok(cursoService.mostrarCurso());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CursoResponse> buscarID(@PathVariable Long id){
        CursoResponse curso = cursoService.buscarID(id);
        return ResponseEntity.ok(curso);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> apagarCursoId(@PathVariable Long id){
        return ResponseEntity.ok(cursoService.deletarCurso(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> alterarCurso(@Valid @PathVariable("id") long id, @RequestBody CursoRequest cursoRequest){
        return ResponseEntity.ok(cursoService.alterarCurso(id, cursoRequest));
    }
}
