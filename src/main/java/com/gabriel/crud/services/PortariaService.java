package com.gabriel.crud.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gabriel.crud.configuration.RabbitMQConfig;
import com.gabriel.crud.domain.entities.Aluno;
import com.gabriel.crud.domain.enums.Movimento;
import com.gabriel.crud.domain.entities.Portaria;
import com.gabriel.crud.repositories.PortariaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
@Slf4j
public class PortariaService {

    private final AlunoService alunoService;
    private final PortariaRepository portariaRepository;
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    public Portaria entrada(Long id) throws JsonProcessingException {
        Aluno aluno = alunoService.getByID(id);
        Portaria ultimoRegistro = portariaRepository.findLastCreatedPortariaByAluno(aluno);
        if (ultimoRegistro != null && ultimoRegistro.getMovimento() == Movimento.ENTRADA) {
            log.error("Aluno já está dentro da escola", new RuntimeException("O Aluno já está dentro da escola"));
            throw new RuntimeException("O Aluno já está dentro da escola");
        }

        Portaria portaria = new Portaria();
        portaria.setAluno(aluno);
        portaria.setData(new Date());
        portaria.setMovimento(Movimento.ENTRADA);
        String portariaJson = objectMapper.writeValueAsString(portaria);

        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, "portaria", portariaJson);

        log.info("Aluno " + aluno.getNomeCompleto() + " entrou na escola as " + portaria.getData());

        return portaria;
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void registrandoEntrada(String portariaJson) {
        try {
            Portaria portaria = objectMapper.readValue(portariaJson, Portaria.class);
            portariaRepository.save(portaria);
            log.info("Registrando entrada do aluno " + portaria.getAluno().getNomeCompleto());
        } catch (Exception e) {
            log.error("Erro ao processar a mensagem da portaria", e);
        }
    }

    public Portaria saida(Long id) {
        Aluno aluno = alunoService.getByID(id);
        Portaria ultimoRegistro = portariaRepository.findLastCreatedPortariaByAluno(aluno);
        if (ultimoRegistro != null && ultimoRegistro.getMovimento() != Movimento.ENTRADA) {
            log.error("O Aluno ainda não entrou na escola",
                    new RuntimeException("O Aluno ainda não está dentro da escola"));
            throw new RuntimeException("O Aluno não está dentro da escola");
        }

        Portaria portariaSaida = new Portaria();
        portariaSaida.setAluno(aluno);
        portariaSaida.setData(new Date());
        portariaSaida.setMovimento(Movimento.SAIDA);

        log.info("Aluno " + aluno.getNomeCompleto() + " saiu da escola as " + portariaSaida.getData());

        return portariaRepository.save(portariaSaida);
    }

}
