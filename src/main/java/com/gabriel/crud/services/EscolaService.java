package com.gabriel.crud.services;

import com.gabriel.crud.controllers.escola.EscolaDto;
import com.gabriel.crud.domain.entities.Escola;
import com.gabriel.crud.repositories.EscolaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EscolaService {
    private final EscolaRepository escolaRepository;

    @Cacheable("escolas")
    public List<Escola> getAll() {
        List<Escola> escolas = new ArrayList<>();
        escolaRepository.findAll().iterator().forEachRemaining(escolas::add);
        return escolas;
    }


    public Escola getById(Long id) {
        return escolaRepository.findById(id).orElseThrow(() -> new RuntimeException("Escola não encontrada"));
    }

    public Escola save(EscolaDto dto) {
        boolean hasBeenExisted = escolaRepository.findByCnpj(dto.getCnpj()) != null;
        if (hasBeenExisted) {
            throw new RuntimeException("Escola com este CNPJ já existente");
        }
        Escola escola = new Escola();
        escola.setNome(dto.getNome());
        escola.setCnpj(dto.getCnpj());
        escola.setEndereco(dto.getEndereco());
        escola.setTelefone(dto.getTelefone());
        return escolaRepository.save(escola);
    }

    public Escola update(Long id, EscolaDto dto) {
        Escola escola = getById(id);
        escola.setNome(dto.getNome());
        escola.setCnpj(dto.getCnpj());
        escola.setEndereco(dto.getEndereco());
        escola.setTelefone(dto.getTelefone());
        return escolaRepository.save(escola);
    }

    public String delete(Long id) {
        escolaRepository.deleteById(id);
        return "Escola has been deleted";
    }
}