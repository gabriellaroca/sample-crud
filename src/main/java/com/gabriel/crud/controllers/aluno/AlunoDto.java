package com.gabriel.crud.controllers.aluno;

import com.gabriel.crud.domain.enums.Genero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlunoDto {
    private String nome;
    private String sobrenome;
    private Genero sexo;
    private Date nascimento;
    private String responsavel;
    private Long sala;
    private Long escola;
}
