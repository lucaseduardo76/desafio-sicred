package com.sessao.voting.pauta.api.controller;


import com.sessao.voting.pauta.domain.Pauta;
import com.sessao.voting.pauta.dto.ContagemDto;
import com.sessao.voting.pauta.dto.PautaRequestDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pauta")
public interface PautaApiController {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void createPauta(@RequestBody @Valid PautaRequestDto pautaRequestDto);

    @GetMapping
    List<Pauta> getPautas();

    @GetMapping("/contagem/{idPauta}")
    ContagemDto getContagemPauta(@PathVariable String idPauta);



}
