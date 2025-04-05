package com.sessao.voting.sessao.api.service;

import com.sessao.voting.sessao.dto.SessaoRequestDto;
import com.sessao.voting.sessao.dto.SessaoResponseDto;
import com.sessao.voting.voto.dto.VotoRequestDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface SessaoService {
    SessaoResponseDto save(SessaoRequestDto sessaoRequestDto);
    SessaoResponseDto findById(String id);

    List<SessaoResponseDto> findAllByPautaId(String idSessao);

    void votar(VotoRequestDto votoRequestDto);
}
