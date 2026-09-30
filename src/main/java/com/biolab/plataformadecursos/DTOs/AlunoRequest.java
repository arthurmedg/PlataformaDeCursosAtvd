package com.biolab.plataformadecursos.DTOs;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlunoRequest {
    @NotBlank
    private String nome;
    @NotBlank
    private String email;
    private long idCurso;
    private Set<CursoRequest> curso;

}
