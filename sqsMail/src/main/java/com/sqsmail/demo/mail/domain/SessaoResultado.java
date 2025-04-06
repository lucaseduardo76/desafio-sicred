package com.sqsmail.demo.mail.domain;


import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
@Data
public class SessaoResultado {
    private List<VotoResponseDto> votos;
    private ContagemDto contagem;
    private LocalDateTime dataHoraCriacao;
    private LocalDateTime dataHoraFim;
    private Pauta pauta;


}
