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
    private String CargaHoraria;
    private Set<AlunoRequest> aluno;
}
