package com.sessao.voting.voto.domain;

import com.sessao.voting.sessao.domain.Sessao;
import com.sessao.voting.voto.dto.VotoRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "tb_voto")
public class Voto {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private VotoEnum voto;
    private Long associado_id;
    private LocalDateTime dataHoraVoto;

    @ManyToOne
    private Sessao sessao;


    public Voto(VotoRequestDto votoRequestDto, Sessao sessao) {
        this.voto = votoRequestDto.getVoto();
        this.associado_id = votoRequestDto.getAssociadoId();
        this.dataHoraVoto = LocalDateTime.now();
        this.sessao = sessao;
    }
}
