package com.sessao.voting.pauta.api.service;

import com.sessao.voting.exception.NotFoundException;
import com.sessao.voting.pauta.domain.Pauta;
import com.sessao.voting.voto.dto.ContagemDto;
import com.sessao.voting.pauta.dto.PautaRequestDto;
import com.sessao.voting.pauta.infra.repository.PautaRepository;
import com.sessao.voting.voto.infra.repository.VotoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class PautaApplicationService implements PautaService {
    private final PautaRepository pautaRepository;
    private final VotoRepository votoRepository;

    @Override
    public void save(PautaRequestDto pautaRequestDto) {
        log.info("[start] PautaApplicationService - save");
        pautaRepository.save(new Pauta(pautaRequestDto));
        log.info("[end] PautaApplicationService - save");
    }

    @Override
    public List<Pauta> findAllPauta() {
        log.info("[start] PautaApplicationService - findAllPauta");
        List<Pauta> pautas = pautaRepository.findAll();
        log.info("[end] PautaApplicationService - findAllPauta");
        return pautas;
    }

    @Override
    public ContagemDto contagem(String idPauta) {
        log.info("[start] PautaApplicationService - contagem");
        Pauta pauta = pautaRepository.findById(idPauta).orElseThrow(
                () -> new NotFoundException("Pauta inexistente")
        );

        ContagemDto contagemDto = votoRepository.contarVotosPorPauta(pauta.getId());
        log.info("[end] PautaApplicationService - contagem");
        return contagemDto;
    }
}
