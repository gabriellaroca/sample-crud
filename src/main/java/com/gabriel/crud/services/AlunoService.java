package com.gabriel.crud.services;

import com.gabriel.crud.controllers.aluno.AlunoDto;
import com.gabriel.crud.domain.entities.Aluno;
import com.gabriel.crud.domain.entities.Sala;
import com.gabriel.crud.repositories.AlunoRepository;
import com.gabriel.crud.repositories.SalaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AlunoService {
    private final AlunoRepository alunoRepository;
    private final SalaRepository salaRepository;

    public List<Aluno> getAllAlunos() {
        List<Aluno> alunos = new ArrayList<>();
        alunoRepository.findAll().forEach(alunos::add);
        return alunos;
    }

    public Aluno getByID(Long id) {
        return alunoRepository.findById(id).orElseThrow(() -> new RuntimeException("Aluno not found"));
    }

    public Aluno save(AlunoDto dto) {
        Sala sala = salaRepository.findById(dto.getSala()).orElseThrow(() -> new RuntimeException("Sala not found"));
        Aluno aluno = new Aluno();
        aluno.setNome(dto.getNome());
        aluno.setSobrenome(dto.getSobrenome());
        aluno.setSexo(dto.getSexo());
        aluno.setNascimento(dto.getNascimento());
        aluno.setResponsavel(dto.getResponsavel());
        aluno.setSala(sala);
        return alunoRepository.save(aluno);
    }

    public Aluno update(Long id, AlunoDto dto) {
        Aluno aluno = getByID(id);
        Sala sala = salaRepository.findById(dto.getSala()).orElseThrow(() -> new RuntimeException("Sala not found"));
        aluno.setNome(dto.getNome());
        aluno.setSala(sala);
        return alunoRepository.save(aluno);
    }

    public String delete(Long id) {
        alunoRepository.deleteById(id);
        return "Aluno has been deleted";
    }
}
