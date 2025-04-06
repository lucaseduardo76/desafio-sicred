package com.sessao.voting.associado.api.controller;


import com.sessao.voting.associado.dto.StatusCpf;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public interface AssociadoApiController {

    @GetMapping("/{cpf}")
    @ResponseStatus(HttpStatus.OK)
    StatusCpf consultaCpf(@PathVariable String cpf);
}
