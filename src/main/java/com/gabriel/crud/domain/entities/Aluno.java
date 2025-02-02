package com.gabriel.crud.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gabriel.crud.domain.enums.Genero;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String sobrenome;

    @Column(nullable = false)
    private Genero sexo;

    @Column(nullable = false)
    private Date nascimento;

    @Column(nullable = false)
    private String responsavel;

    @ManyToOne
    @JoinColumn(name = "sala_aluno")
    @JsonIgnoreProperties("alunos")
    private Sala sala;

    @OneToMany
    @JoinColumn(name="aluno_portaria")
    @JsonIgnore
    private List<Portaria> portaria;
}