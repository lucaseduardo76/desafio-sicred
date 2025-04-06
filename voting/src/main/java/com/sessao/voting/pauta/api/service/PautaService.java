package com.sessao.voting.pauta.api.service;

import com.sessao.voting.pauta.domain.Pauta;
import com.sessao.voting.voto.dto.ContagemDto;
import com.sessao.voting.pauta.dto.PautaRequestDto;

import java.util.List;


public interface PautaService {
    void save(PautaRequestDto pautaRequestDto);

    List<Pauta> findAllPauta();

    ContagemDto contagem(String idPauta);
}
