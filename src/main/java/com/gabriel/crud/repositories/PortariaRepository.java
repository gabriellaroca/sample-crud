package com.gabriel.crud.repositories;

import com.gabriel.crud.domain.entities.Aluno;
import com.gabriel.crud.domain.entities.Portaria;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PortariaRepository extends CrudRepository<Portaria, Long> {
    @Query("SELECT p FROM Portaria p WHERE p.aluno = :aluno ORDER BY p.data DESC LIMIT 1")
    Portaria findLastCreatedPortariaByAluno(Aluno aluno);
}
