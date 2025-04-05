package com.sessao.voting.pauta.infra.repository;

import com.sessao.voting.pauta.domain.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PautaRepository extends JpaRepository<Pauta, String> {
}
