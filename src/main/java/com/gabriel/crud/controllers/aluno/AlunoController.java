package com.gabriel.crud.controllers.aluno;

import com.gabriel.crud.domain.entities.Aluno;
import com.gabriel.crud.services.AlunoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aluno")
@RequiredArgsConstructor
public class AlunoController {
    private final AlunoService alunoService;

    @GetMapping
    public List<Aluno> getAllAlunos() {
        return alunoService.getAllAlunos();
    }

    @GetMapping("/{id}")
    public Aluno getByID(@PathVariable Long id) {
        return alunoService.getByID(id);
    }

    @PostMapping
    public Aluno save(@RequestBody AlunoDto dto) {
        return alunoService.save(dto);
    }

    @PutMapping("/{id}")
    public Aluno update(@PathVariable Long id, @RequestBody AlunoDto dto) {
        return alunoService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        return alunoService.delete(id);
    }
}