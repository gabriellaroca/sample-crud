package com.gabriel.crud.domain.entities;

import com.gabriel.crud.domain.enums.Movimento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Portaria implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "aluno_portaria")
    @ManyToOne
    private Aluno aluno;

    @Column(nullable = false)
    private Movimento movimento;

    @Column(nullable = false)
    private Date data;
}
