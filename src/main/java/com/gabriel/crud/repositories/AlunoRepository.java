package com.gabriel.crud.repositories;

import com.gabriel.crud.domain.entities.Aluno;
import org.springframework.data.repository.CrudRepository;

public interface AlunoRepository extends CrudRepository<Aluno, Long> {
}
