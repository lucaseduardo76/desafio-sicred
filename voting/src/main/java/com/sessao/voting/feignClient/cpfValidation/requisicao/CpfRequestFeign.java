package com.sessao.voting.feignClient.cpfValidation.requisicao;


import com.sessao.voting.feignClient.cpfValidation.domain.CpfResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;


@FeignClient(name = "cpfRequestFeign", url = "${cpf.request.url}" )
public interface CpfRequestFeign {

    @GetMapping("/cpf/{cpf}")
    ResponseEntity<CpfResponse> consultarCpf(
            @PathVariable("cpf") String cpf,
            @RequestHeader("Authorization") String authorization
    );
}
