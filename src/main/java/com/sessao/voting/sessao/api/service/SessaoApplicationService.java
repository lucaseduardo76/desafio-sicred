package com.sessao.voting.sessao.api.service;

import com.sessao.voting.associado.domain.Associado;
import com.sessao.voting.associado.infra.repository.AssociadoRepository;
import com.sessao.voting.exception.NotFoundException;
import com.sessao.voting.pauta.domain.Pauta;
import com.sessao.voting.pauta.infra.repository.PautaRepository;
import com.sessao.voting.sessao.domain.Sessao;
import com.sessao.voting.sessao.dto.SessaoRequestDto;
import com.sessao.voting.sessao.dto.SessaoResponseDto;
import com.sessao.voting.sessao.infra.repository.SessaoRepository;
import com.sessao.voting.voto.domain.Voto;
import com.sessao.voting.voto.dto.VotoRequestDto;
import com.sessao.voting.voto.infra.repository.VotoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class SessaoApplicationService implements SessaoService {

    private final SessaoRepository sessaoRepository;
    private final PautaRepository pautaRepository;
    private final AssociadoRepository associadoRepository;
    private final VotoRepository votoRepository;

    @Override
    public SessaoResponseDto save(SessaoRequestDto sessaoRequestDto) {
        log.info("[start] SessaoApplicationService - save");
        Pauta pauta = pautaRepository.findById(sessaoRequestDto.getPautaId()).orElseThrow(
                () -> new NotFoundException("Pauta não encontrada")
        );

        Sessao sessao = new Sessao(sessaoRequestDto, pauta);
        SessaoResponseDto sessaoResponseDto = new SessaoResponseDto(sessaoRepository.save(sessao));
        log.info("[end] SessaoApplicationService - save");

        return sessaoResponseDto;
    }

    @Override
    public SessaoResponseDto findById(String id) {
        log.info("[start] SessaoApplicationService - findById");
        Sessao sessao = sessaoRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Sessao não encontrada")
        );
        SessaoResponseDto sessaoResponseDto = new SessaoResponseDto(sessao);

        log.info("[end] SessaoApplicationService - findById");
        return sessaoResponseDto;
    }

    @Override
    public List<SessaoResponseDto> findAllByPautaId(String idSessao) {
        log.info("[start] SessaoApplicationService - findAllByPautaId");
        Pauta pauta = pautaRepository.findById(idSessao).orElseThrow(
                () -> new NotFoundException("Pauta não encontrada")
        );

        List<SessaoResponseDto> sessaoResponseDto = sessaoRepository.findAllByPauta(pauta).stream()
                .map(SessaoResponseDto::new).collect(Collectors.toList());

        log.info("[end] SessaoApplicationService - findAllByPautaId");

        return sessaoResponseDto;
    }

    @Override
    public void votar(VotoRequestDto votoRequestDto) {
        log.info("[start] SessaoApplicationService - votar");

        Sessao sessao = sessaoRepository.findById(votoRequestDto.getSessaoId()).orElseThrow(
                () -> new NotFoundException("Sessão não encontrada")
        );

        Associado associado = associadoRepository.findById(votoRequestDto.getAssociadoId()).orElseThrow(
                () -> new NotFoundException("Associado não encontrado")
        );

        Voto voto = new Voto(votoRequestDto, sessao);
        sessao.vote(voto);
        sessaoRepository.save(sessao);
        
        log.info("[end] SessaoApplicationService - votar");
    }
}
