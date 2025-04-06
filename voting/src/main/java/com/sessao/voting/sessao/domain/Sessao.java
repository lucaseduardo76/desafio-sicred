package com.sessao.voting.sessao.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sessao.voting.exception.BadRequestException;
import com.sessao.voting.pauta.domain.Pauta;
import com.sessao.voting.sessao.dto.SessaoRequestDto;
import com.sessao.voting.voto.domain.Voto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "tb_sessao")
public class Sessao {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @OneToMany(mappedBy = "sessao", cascade = CascadeType.ALL, orphanRemoval = true)
    @MapKey(name = "associado_id")
    private Map<Long, Voto> votos = new HashMap<>();

    private LocalDateTime dataHoraCriacao;
    private LocalDateTime dataHoraFim;

    private SessaoStatus status;

    @JsonIgnore
    @ManyToOne
    private Pauta pauta;

    public Sessao(SessaoRequestDto sessaoRequestDto, Pauta pauta) {
        this.status = SessaoStatus.ABERTO;
        this.pauta = pauta;
        this.dataHoraCriacao = LocalDateTime.now();

        if(sessaoRequestDto.getDuracaoMinutos() != null) {
            this.dataHoraFim = LocalDateTime.now().plusMinutes(sessaoRequestDto.getDuracaoMinutos());
        }else {
            this.dataHoraFim = LocalDateTime.now().plusMinutes(1);
        }

    }

    public void vote(Voto voto) {

        isSessaoOpen();
        isAbleToVote(voto);

        votos.put(voto.getAssociado_id(), voto);

    }

    private void isAbleToVote(Voto voto) {
        pauta.getSessao().forEach(sessao -> {
            if(sessao.getVotos().containsKey(voto.getAssociado_id())){
                throw new BadRequestException("Usuario já votou nessa pauta");
            }
        });
    }

    private void isSessaoOpen(){
        closeSessaoIfExpired();
        if(status == SessaoStatus.FECHADO)
            throw new BadRequestException("Sessão Fechada");
    }

    public Boolean isSessaoClosed(){
        closeSessaoIfExpired();
        return SessaoStatus.FECHADO == this.status;
    }

    public void closeSessaoIfExpired() {
        if(dataHoraFim.isBefore(LocalDateTime.now())) {
            this.status = SessaoStatus.FECHADO;
        }
    }




}
