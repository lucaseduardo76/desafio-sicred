package com.sessao.voting.voto.infra.repository;

import com.sessao.voting.voto.dto.ContagemDto;
import com.sessao.voting.voto.domain.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VotoRepository extends JpaRepository<Voto, String> {
    @Query("""
        SELECT new com.sessao.voting.voto.dto.ContagemDto(
            SUM(CASE WHEN v.voto = com.sessao.voting.voto.domain.VotoEnum.SIM THEN 1 ELSE 0 END),
            SUM(CASE WHEN v.voto = com.sessao.voting.voto.domain.VotoEnum.NAO THEN 1 ELSE 0 END),
            COUNT(v)
        )
        FROM Voto v
        WHERE v.sessao.pauta.id = :pautaId
    """)
    ContagemDto contarVotosPorPauta(@Param("pautaId") String pautaId);
}
