package com.sessao.voting.voto.dto;

import com.sessao.voting.voto.domain.Voto;
import com.sessao.voting.voto.domain.VotoEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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


    public ContagemDto(Map<Long, Voto> votosMap) {
        Map<VotoEnum, Long> contagem = votosMap.values().stream()
                .collect(Collectors.groupingBy(Voto::getVoto, Collectors.counting()));

        long votosSim = contagem.getOrDefault(VotoEnum.SIM, 0L);
        long votosNao = contagem.getOrDefault(VotoEnum.NAO, 0L);
        long total = votosMap.size();

        this.votoSim = votosSim;
        this.votoNao = votosNao;
        this.totalVotos = total;
    }
}
