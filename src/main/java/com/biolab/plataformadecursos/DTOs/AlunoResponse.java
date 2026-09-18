package com.biolab.plataformadecursos.DTOs;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlunoResponse {
    private long id;
    private String nome;
    private String email;

    public AlunoResponse(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

}
