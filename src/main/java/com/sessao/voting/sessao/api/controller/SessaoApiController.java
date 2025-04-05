package com.sessao.voting.sessao.api.controller;

import com.sessao.voting.sessao.dto.SessaoRequestDto;
import com.sessao.voting.sessao.dto.SessaoResponseDto;
import com.sessao.voting.voto.dto.VotoRequestDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sessao")
public interface SessaoApiController {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    SessaoResponseDto post(@RequestBody SessaoRequestDto sessaoRequestDto);

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    SessaoResponseDto get(@PathVariable String id);

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<SessaoResponseDto> getAllFromPauta(@RequestParam String idPauta);

    @PatchMapping("/votar")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void votar(@RequestBody VotoRequestDto votoRequestDto);
}
