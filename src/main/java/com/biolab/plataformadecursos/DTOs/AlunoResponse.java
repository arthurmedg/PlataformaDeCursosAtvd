package com.biolab.plataformadecursos.DTOs;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlunoResponse {
    private long id;
    private String nome;
    private String email;
    private Set<CursoRequest> curso;

    public AlunoResponse(@NotBlank long id, @NotBlank String nome, @NotBlank String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    public AlunoResponse(String nome, String email, Set<CursoRequest> curso) {
        this.nome = nome;
        this.email = email;
        this.curso = curso;
    }
}
