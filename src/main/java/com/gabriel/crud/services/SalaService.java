package com.gabriel.crud.services;

import com.gabriel.crud.controllers.sala.SalaDto;
import com.gabriel.crud.domain.entities.Escola;
import com.gabriel.crud.domain.entities.Sala;
import com.gabriel.crud.repositories.EscolaRepository;
import com.gabriel.crud.repositories.SalaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SalaService {

    private final EscolaRepository escolaRepository;
    private final SalaRepository salaRepository;

    public Sala save(SalaDto model) {
        Escola escola = escolaRepository.findById(model.getEscola()).orElseThrow(()-> new RuntimeException("Escola não encontrado"));
        Sala sala = new Sala();
        sala.setNome(model.getNome());
        sala.setEscola(escola);
        sala.setNivel(model.getNivel());
        return salaRepository.save(sala);
    }

}
