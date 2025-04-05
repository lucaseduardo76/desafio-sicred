package com.sessao.voting.associado.infra.repository;

import com.sessao.voting.associado.domain.Associado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AssociadoRepository extends JpaRepository<Associado, Long> {
}
