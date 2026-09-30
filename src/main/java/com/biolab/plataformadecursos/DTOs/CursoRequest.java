package com.biolab.plataformadecursos.DTOs;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CursoRequest {
    @NotBlank
    private String nome;
    @NotBlank
    private String cargaHoraria;
    private Set<AlunoRequest> aluno;

    public CursoRequest(String nome, String cargaHoraria) {
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
    }
}
