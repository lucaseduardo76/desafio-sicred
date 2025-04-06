package com.sessao.voting.associado.api.service;

import com.sessao.voting.associado.dto.StatusCpf;
import com.sessao.voting.feignClient.cpfValidation.domain.CpfResponse;
import com.sessao.voting.feignClient.cpfValidation.domain.Situacao;
import com.sessao.voting.feignClient.cpfValidation.requisicao.CpfRequestFeign;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Log4j2
public class AssiciadoApplicationService implements AssiciadoService {
    private final CpfRequestFeign cpfRequestFeign;

    @Value("${cpf.request.token}")
    private String token;

    @Override
    public StatusCpf verifyCpf(String cpf) {
       log.info("[start] AssiciadoApplicationService - verifyCpf");
        ResponseEntity<CpfResponse> response = cpfRequestFeign.consultarCpf(cpf, token);

        Situacao situacao = Objects.requireNonNull(response.getBody()).getSituacao();


       log.info("[end] AssiciadoApplicationService - verifyCpf");
        return new StatusCpf(situacao);
    }
}
