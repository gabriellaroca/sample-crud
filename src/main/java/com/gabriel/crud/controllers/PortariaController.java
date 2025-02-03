package com.gabriel.crud.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gabriel.crud.domain.entities.Portaria;
import com.gabriel.crud.services.PortariaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/portaria")
@RequiredArgsConstructor
public class PortariaController {

    private final PortariaService portariaService;

    @PostMapping("/entrada/{id}")
    public Portaria entradaAluno(@PathVariable("id") Long id) throws JsonProcessingException{
        return portariaService.entrada(id);
    }

    @PostMapping("/saida/{aluno_id}")
    public Portaria saidaAluno(@PathVariable("aluno_id") Long id){
        return portariaService.saida(id);
    }

}
