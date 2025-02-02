package com.gabriel.crud.controllers.escola;

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
    public Escola save(@RequestBody EscolaDto dto) {
        return escolaService.save(dto);
    }

    @PutMapping("/{id}")
    public Escola update(@PathVariable Long id, @RequestBody EscolaDto dto) {
        return escolaService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        return escolaService.delete(id);
    }
}
