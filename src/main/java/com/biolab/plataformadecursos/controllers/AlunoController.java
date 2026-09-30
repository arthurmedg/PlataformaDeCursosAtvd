package com.biolab.plataformadecursos.controllers;

import com.biolab.plataformadecursos.DTOs.AlunoRequest;
import com.biolab.plataformadecursos.DTOs.AlunoResponse;
import com.biolab.plataformadecursos.services.AlunoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class AlunoController {
    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }


    @PostMapping
    public ResponseEntity<?> criarAluno(@Valid @RequestBody AlunoRequest alunoRequest) {
        return ResponseEntity.ok("Criado com sucesso!" + alunoService.criarAluno(alunoRequest));
    }
}
