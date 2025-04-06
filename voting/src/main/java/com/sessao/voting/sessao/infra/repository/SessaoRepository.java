package com.sessao.voting.sessao.infra.repository;

import com.sessao.voting.pauta.domain.Pauta;
import com.sessao.voting.sessao.domain.Sessao;
import com.sessao.voting.sessao.domain.SessaoStatus;
import com.sessao.voting.sessao.dto.SessaoResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SessaoRepository extends JpaRepository<Sessao, String> {
    List<Sessao> findAllByPauta(Pauta pauta);

    @Query("SELECT s FROM Sessao s LEFT JOIN FETCH s.votos WHERE s.status = :status")
    List<Sessao> findAllByStatusWithVotos(@Param("status") SessaoStatus status);
}
