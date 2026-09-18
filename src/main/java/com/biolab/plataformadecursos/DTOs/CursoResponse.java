package com.biolab.plataformadecursos.DTOs;

import jakarta.validation.constraints.NotBlank;

public class CursoResponse {
    private long id;
    private String nome;
    private String CargaHoraria;

    public CursoResponse(String nome, String cargaHoraria) {
        this.nome = nome;
        CargaHoraria = cargaHoraria;
    }
}
