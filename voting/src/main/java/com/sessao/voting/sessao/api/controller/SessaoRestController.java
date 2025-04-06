package com.sessao.voting.sessao.api.controller;

import com.sessao.voting.sessao.api.service.SessaoService;
import com.sessao.voting.sessao.dto.SessaoRequestDto;
import com.sessao.voting.sessao.dto.SessaoResponseDto;
import com.sessao.voting.voto.dto.VotoRequestDto;
import jakarta.servlet.Servlet;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.antlr.v4.runtime.misc.LogManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sessao")
@Log4j2
@RequiredArgsConstructor
public class SessaoRestController implements SessaoApiController {
    private final SessaoService sessaoService;

    @Override
    public SessaoResponseDto post(SessaoRequestDto sessaoRequestDto) {
        log.info("[start] SessaoRestController - post");
        SessaoResponseDto sessaoResponseDto = sessaoService.save(sessaoRequestDto);
        log.info("[end] SessaoRestController - post");
        return sessaoResponseDto;
    }

    @Override
    public SessaoResponseDto get(String id) {
        log.info("[start] SessaoRestController - get");
        SessaoResponseDto  sessaoResponseDto = sessaoService.findById(id);
        log.info("[end] SessaoRestController - get");
        return sessaoResponseDto;
    }

    @Override
    public List<SessaoResponseDto> getAllFromPauta(String idPauta) {
        log.info("[start] SessaoRestController - getAllFromSection");
        List<SessaoResponseDto> sessaoResponseDtos = sessaoService.findAllByPautaId(idPauta);
        log.info("[end] SessaoRestController - getAllFromSection");
        return sessaoResponseDtos;
    }

    @Override
    public void votar(VotoRequestDto votoRequestDto) {
        log.info("[start] SessaoRestController - votar");
        sessaoService.votar(votoRequestDto);
        log.info("[end] SessaoRestController - votar");
    }


}
