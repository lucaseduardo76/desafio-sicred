package com.sessao.voting.associado.api.service;

import com.sessao.voting.associado.dto.StatusCpf;

public interface AssiciadoService {
    StatusCpf verifyCpf(String cpf);
}
