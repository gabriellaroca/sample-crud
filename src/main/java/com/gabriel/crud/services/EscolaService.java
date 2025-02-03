package com.gabriel.crud.services;

import com.gabriel.crud.domain.entities.Escola;
import com.gabriel.crud.domain.entities.Sala;
import com.gabriel.crud.repositories.EscolaRepository;
import com.gabriel.crud.repositories.SalaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EscolaService {
    private final EscolaRepository escolaRepository;
    private final SalaRepository salaRepository;

    @Cacheable("escolas")
    public List<Escola> getAll() {
        List<Escola> escolas = new ArrayList<>();
        escolaRepository.findAll().iterator().forEachRemaining(escolas::add);
        return escolas;
    }

    public Escola getById(Long id) {
        return escolaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Escola não encontrada"));
    }

    @Transactional
    public Escola save(Escola dto) {
        if (dto.getSalas() == null || dto.getSalas().isEmpty()) {
            throw new RuntimeException("Escola deve ter pelo menos uma sala");
        }

        if (escolaRepository.findByCnpj(dto.getCnpj()) != null) {
            throw new RuntimeException("Escola com este CNPJ já existente");
        }

        Escola escola = escolaRepository.save(new Escola(null, dto.getNome(), dto.getCnpj(), dto.getEndereco(), dto.getTelefone(), null));

        for (Sala sala : dto.getSalas()) {
            sala.setEscola(escola);
            salaRepository.save(sala);
        }

        escola.setSalas(dto.getSalas());
        log.info("Escola {} criada com sucesso", escola.getNome());
        return escola;
    }

    @Transactional
    public Escola update(Long id, Escola dto) {
        Escola escola = getById(id);
        
        escola.setNome(dto.getNome() != null ? dto.getNome() : escola.getNome());
        escola.setCnpj(dto.getCnpj() != null ? dto.getCnpj() : escola.getCnpj());
        escola.setEndereco(dto.getEndereco() != null ? dto.getEndereco() : escola.getEndereco());
        escola.setTelefone(dto.getTelefone() != null ? dto.getTelefone() : escola.getTelefone());

        if (dto.getSalas() != null && !dto.getSalas().isEmpty()) {
            for (Sala sala : dto.getSalas()) {
                if (sala.getId() == null) {
                    sala.setEscola(escola);
                    salaRepository.save(sala);
                } else {
                    Sala salaExistente = salaRepository.findById(sala.getId())
                            .orElseThrow(() -> new RuntimeException("Sala não encontrada"));
                    salaExistente.setNome(sala.getNome());
                    salaExistente.setNivel(sala.getNivel());
                    salaRepository.save(salaExistente);
                }
            }
        }

        escolaRepository.save(escola);
        log.info("Escola {} atualizada com sucesso", escola.getNome());
        return escola;
    }

    @Transactional
    public void delete(Long id) {
        Escola escola = getById(id);
        if (!escola.getSalas().isEmpty()) {
            throw new RuntimeException("Não é possível deletar a escola pois existem salas vinculadas");
        }
        escolaRepository.delete(escola);
        log.info("Escola com id {} deletada com sucesso", id);
    }
}
