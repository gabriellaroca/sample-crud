package com.gabriel.crud.services;

import com.gabriel.crud.domain.entities.Aluno;
import com.gabriel.crud.domain.enums.Movimento;
import com.gabriel.crud.domain.entities.Portaria;
import com.gabriel.crud.repositories.PortariaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class PortariaService {

    private final AlunoService alunoService;
    private final PortariaRepository portariaRepository;

    public Portaria entrada(Long id) {
        Aluno aluno = alunoService.getByID(id);
        Portaria ultimoRegistro = portariaRepository.findLastCreatedPortariaByAluno(aluno);
        if (ultimoRegistro!=null && ultimoRegistro.getMovimento() == Movimento.ENTRADA) {
            throw new RuntimeException("O Aluno já está dentro da escola");
        }

        Portaria portaria = new Portaria();
        portaria.setAluno(aluno);
        portaria.setData(new Date());
        portaria.setMovimento(Movimento.ENTRADA);

        return portariaRepository.save(portaria);
    }

    public Portaria saida(Long id) {
        Aluno aluno = alunoService.getByID(id);
        Portaria ultimoRegistro = portariaRepository.findLastCreatedPortariaByAluno(aluno);
        if(ultimoRegistro != null && ultimoRegistro.getMovimento() != Movimento.ENTRADA) {
            throw new RuntimeException("O Aluno não está dentro da escola");
        }

        Portaria portariaSaida = new Portaria();
        portariaSaida.setAluno(aluno);
        portariaSaida.setData(new Date());
        portariaSaida.setMovimento(Movimento.SAIDA);

        return portariaRepository.save(portariaSaida);
    }

}
