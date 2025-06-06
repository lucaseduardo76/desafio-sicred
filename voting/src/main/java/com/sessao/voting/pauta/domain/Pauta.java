package com.sessao.voting.pauta.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sessao.voting.pauta.dto.PautaRequestDto;
import com.sessao.voting.sessao.domain.Sessao;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "tb_pauta")
public class Pauta {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @JsonIgnore
    @OneToMany(mappedBy = "pauta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Sessao> sessao;

    private String descricao;
    private String email;

    public Pauta(PautaRequestDto pautaRequestDto) {
        this.descricao = pautaRequestDto.getDescricao();
        this.email = pautaRequestDto.getEmail();
    }
}
