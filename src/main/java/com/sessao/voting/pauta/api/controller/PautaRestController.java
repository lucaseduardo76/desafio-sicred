package com.sessao.voting.pauta.api.controller;

import com.sessao.voting.pauta.api.service.PautaService;
import com.sessao.voting.pauta.domain.Pauta;
import com.sessao.voting.pauta.dto.ContagemDto;
import com.sessao.voting.pauta.dto.PautaRequestDto;
import com.sessao.voting.sessao.api.service.SessaoApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/pauta")
@RequiredArgsConstructor
@Log4j2
public class PautaRestController implements PautaApiController {
    private final PautaService pautaService;

    @Override
    public void createPauta(@RequestBody @Valid PautaRequestDto pautaRequestDto) {
        log.info("[start] PautaRestController - createPauta");
        pautaService.save(pautaRequestDto);
        log.info("[end] PautaRestController - createPauta");
    }

    @Override
    public List<Pauta> getPautas() {
        log.info("[start] PautaRestController - getPautas");
        var pautas = pautaService.findAllPauta();
        log.info("[end] PautaRestController - getPautas");
        return pautas;
    }

    @Override
    public ContagemDto getContagemPauta(String idPauta) {
        log.info("[start] PautaRestController - getContagemPauta");
        ContagemDto contagem = pautaService.contagem(idPauta);

        log.info("[end] PautaRestController - getContagemPauta");
        return contagem;
    }


}
