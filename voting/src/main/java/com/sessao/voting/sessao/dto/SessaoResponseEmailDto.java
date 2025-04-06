package com.sessao.voting.sessao.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sessao.voting.pauta.domain.Pauta;
import com.sessao.voting.sessao.domain.Sessao;
import com.sessao.voting.voto.dto.ContagemDto;
import com.sessao.voting.voto.dto.VotoResponseDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SessaoResponseEmailDto {


    private List<VotoResponseDto> votos;
    private ContagemDto contagem;
    private LocalDateTime dataHoraCriacao;
    private LocalDateTime dataHoraFim;
    private Pauta pauta;

    public SessaoResponseEmailDto(Sessao sessao) {
        this.dataHoraCriacao = sessao.getDataHoraCriacao();
        this.dataHoraFim = sessao.getDataHoraFim();
        this.pauta = sessao.getPauta();
        this.votos = sessao.getVotos()
                .values()
                .stream()
                .map(VotoResponseDto::new)
                .collect(Collectors.toList());
        this.contagem = new ContagemDto(sessao.getVotos());
    }

}
