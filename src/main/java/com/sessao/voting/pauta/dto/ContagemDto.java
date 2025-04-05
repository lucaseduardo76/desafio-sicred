package com.sessao.voting.pauta.dto;

import com.sessao.voting.pauta.domain.Pauta;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ContagemDto {

    private Long votoSim;
    private Long votoNao;
    private Long totalVotos;


    public ContagemDto(Long votoSim, Long votoNao, Long totalVotos) {
        this.votoSim = votoSim;
        this.votoNao = votoNao;
        this.totalVotos = totalVotos;
    }

}
