package com.gabriel.crud.controllers.escola;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EscolaDto {
    private String nome;
    private String cnpj;
    private String endereco;
    private String telefone;
}
