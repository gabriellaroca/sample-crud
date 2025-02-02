package com.gabriel.crud.controllers.sala;

import com.gabriel.crud.domain.enums.Nivel;
import lombok.Data;

@Data
public class SalaDto {
    private String nome;
    private Nivel nivel;
    private Long escola;
}
