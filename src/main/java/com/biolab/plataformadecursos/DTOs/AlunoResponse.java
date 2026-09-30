package com.biolab.plataformadecursos.DTOs;

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
    private Set<CursoResponse> cursos;

    public AlunoResponse(long id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }
}