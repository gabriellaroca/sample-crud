package com.gabriel.crud.services;

import com.gabriel.crud.domain.entities.Aluno;
import com.gabriel.crud.domain.entities.Sala;
import com.gabriel.crud.repositories.AlunoRepository;
import com.gabriel.crud.repositories.SalaRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AlunoService {
    private final AlunoRepository alunoRepository;
    private final SalaRepository salaRepository;

    public List<Aluno> getAllAlunos() {
        List<Aluno> alunos = new ArrayList<>();
        alunoRepository.findAll().forEach(alunos::add);
        return alunos;
    }

    public Aluno getByID(Long id) {
        return alunoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
    }

    public Aluno save(Aluno aluno) {
        if (aluno.getSala() == null) {
            throw new RuntimeException("Aluno deve estar vinculado a uma sala");
        }

        if (aluno.getId() != null && alunoRepository.existsById(aluno.getId())) {
            throw new RuntimeException("Aluno já cadastrado");
        }

        Sala sala = salaRepository.findById(aluno.getSala().getId())
                .orElseThrow(() -> new RuntimeException("Sala não encontrada"));

        aluno.setSala(sala);
        Aluno alunoSalvo = alunoRepository.save(aluno);
        log.info("Aluno {} criado com sucesso", alunoSalvo.getNomeCompleto());
        return alunoSalvo;
    }

    public Aluno update(Long id, Aluno dto) {
        if (id == null) {
            throw new RuntimeException("Id do aluno não pode ser nulo");
        }

        Aluno aluno = getByID(id);

        if (dto.getSala() != null && !dto.getSala().equals(aluno.getSala())) {
            throw new RuntimeException("Aluno já pertence a uma sala e não pode ser transferido");
        }

        aluno.setNome(dto.getNome() != null ? dto.getNome() : aluno.getNome());
        aluno.setSobrenome(dto.getSobrenome() != null ? dto.getSobrenome() : aluno.getSobrenome());
        aluno.setNascimento(dto.getNascimento() != null ? dto.getNascimento() : aluno.getNascimento());
        aluno.setResponsavel(dto.getResponsavel() != null ? dto.getResponsavel() : aluno.getResponsavel());
        aluno.setSexo(dto.getSexo() != null ? dto.getSexo() : aluno.getSexo());

        Aluno alunoAtualizado = alunoRepository.save(aluno);
        log.info("Aluno {} atualizado com sucesso", alunoAtualizado.getNomeCompleto());
        return alunoAtualizado;
    }

    public void delete(Long id) {
        Aluno aluno = getByID(id);
        alunoRepository.delete(aluno);
        log.info("Aluno {} deletado com sucesso", aluno.getNomeCompleto());
    }
}
