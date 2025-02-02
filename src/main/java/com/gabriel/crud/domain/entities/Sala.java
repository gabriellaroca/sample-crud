package com.gabriel.crud.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gabriel.crud.domain.enums.Nivel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sala {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private Nivel nivel;

    @ManyToOne()
    @JoinColumn(name = "escola_sala")
    @JsonIgnoreProperties("salas")
    private Escola escola;

    @OneToMany()
    @JoinColumn(name = "sala_aluno")
    @JsonIgnoreProperties("sala")
    private List<Aluno> alunos;
}
