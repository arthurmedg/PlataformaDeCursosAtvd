package com.biolab.plataformadecursos.controllers;

import com.biolab.plataformadecursos.DTOs.AlunoRequest;
import com.biolab.plataformadecursos.DTOs.AlunoResponse;
import com.biolab.plataformadecursos.services.AlunoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("aluno")
public class AlunoController {
    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }


    @PostMapping
    public ResponseEntity<?> criarAluno(@RequestBody AlunoRequest alunoRequest) {
        return ResponseEntity.ok("Criado com sucesso!" + alunoService.criarAluno(alunoRequest));
    }

    @GetMapping
    public ResponseEntity<?> mostrarAluno() {
        return ResponseEntity.ok(alunoService.mostrarAlunos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoResponse> buscarID(@PathVariable Long id){
        AlunoResponse aluno = alunoService.buscarID(id);
        return ResponseEntity.ok(aluno);
    }

    @DeleteMapping
    public ResponseEntity<?> deletarAluno(@PathVariable Long id){
        return ResponseEntity.ok(alunoService.deletarAluno(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> alterarAluno(@Valid @PathVariable("id") long id, @RequestBody AlunoRequest alunoRequest){
        return ResponseEntity.ok(alunoService.alterarAluno(id, alunoRequest));
    }
}
