package com.gabriel.crud.controllers;

import com.gabriel.crud.domain.entities.Escola;
import com.gabriel.crud.services.EscolaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/escola")
@RequiredArgsConstructor
public class EscolaController {
    private final EscolaService escolaService;

    @GetMapping
    public List<Escola> getAll() {
        return escolaService.getAll();
    }

    @GetMapping("/{id}")
    public Escola getById(@PathVariable Long id) {
        return escolaService.getById(id);
    }

    @PostMapping
    public Escola save(@RequestBody Escola escola) {
        return escolaService.save(escola);
    }

    @PutMapping("/{id}")
    public Escola update(@PathVariable Long id, @RequestBody Escola escola) {
        return escolaService.update(id, escola);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        escolaService.delete(id);
    }
}
