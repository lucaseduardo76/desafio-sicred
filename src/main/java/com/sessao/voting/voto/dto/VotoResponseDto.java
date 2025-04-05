package com.sessao.voting.voto.dto;

import com.sessao.voting.associado.domain.Associado;
import com.sessao.voting.voto.domain.Voto;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class VotoResponseDto {
    private Enum voto;
    private LocalDateTime dataHoraVoto;

    public VotoResponseDto(Voto votoDomain) {
        this.voto = votoDomain.getVoto();
        this.dataHoraVoto = votoDomain.getDataHoraVoto();

    }
}
