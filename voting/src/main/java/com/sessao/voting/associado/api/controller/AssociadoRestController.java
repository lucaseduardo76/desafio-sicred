package com.sessao.voting.associado.api.controller;

import com.sessao.voting.associado.api.service.AssiciadoService;
import com.sessao.voting.associado.dto.StatusCpf;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Log4j2
public class AssociadoRestController implements AssociadoApiController {
    private final AssiciadoService associadoService;

    @Override
    public StatusCpf consultaCpf(String cpf) {
        log.info("[start] AssociadoRestController - consultaCpf");
        StatusCpf statusCpf = associadoService.verifyCpf(cpf);

        log.info("[end] AssociadoRestController - consultaCpf");
        return statusCpf;
    }
}
