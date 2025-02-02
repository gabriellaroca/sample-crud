package com.gabriel.crud.controllers.sala;

import com.gabriel.crud.domain.entities.Sala;
import com.gabriel.crud.services.SalaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sala")
@RequiredArgsConstructor
public class SalaController {

    private final SalaService salaService;

    @PostMapping()
    public Sala sala(@RequestBody SalaDto model) {
       return salaService.save(model);
    }

}
