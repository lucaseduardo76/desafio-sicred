package com.sessao.voting.sessao.infra.repository;

import com.sessao.voting.pauta.domain.Pauta;
import com.sessao.voting.sessao.domain.Sessao;
import com.sessao.voting.sessao.dto.SessaoResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SessaoRepository extends JpaRepository<Sessao, String> {
    List<Sessao> findAllByPauta(Pauta pauta);
}
