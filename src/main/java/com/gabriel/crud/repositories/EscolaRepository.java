package com.gabriel.crud.repositories;

import com.gabriel.crud.domain.entities.Escola;
import org.springframework.data.repository.CrudRepository;

public interface EscolaRepository extends CrudRepository<Escola, Long> {
    Escola findByCnpj(String cnpj);
}
