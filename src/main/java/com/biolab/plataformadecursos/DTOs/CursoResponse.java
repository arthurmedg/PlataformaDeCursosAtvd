package com.biolab.plataformadecursos.DTOs;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CursoResponse {
    private long id;
    private String nome;
    private String cargaHoraria;
    private Set<AlunoRequest> aluno;


    public CursoResponse(long id, String nome, String cargaHoraria) {
            this.id = id;
            this.nome = nome;
            this.cargaHoraria = cargaHoraria;
    }

    public CursoResponse(String nome, String cargaHoraria, Set<AlunoRequest> aluno) {
            this.nome = nome;
            this.cargaHoraria = cargaHoraria;
            this.aluno = aluno;
    }
}




