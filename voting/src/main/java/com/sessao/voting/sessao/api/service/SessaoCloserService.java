package com.sessao.voting.sessao.api.service;

import com.sessao.voting.infra.sns.SnsNotificationService;
import com.sessao.voting.sessao.domain.Sessao;
import com.sessao.voting.sessao.domain.SessaoStatus;
import com.sessao.voting.sessao.dto.SessaoResponseEmailDto;
import com.sessao.voting.sessao.infra.repository.SessaoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Log4j2
public class SessaoCloserService {
    private final SessaoRepository sessaoRepository;

    private final SnsNotificationService snsNotificationService;

    @Value("${spring.cloud.aws.sns.topic.name}")
    private String topicSns;

    @Scheduled(cron = "0 * * * * *")
    public void closeAllExpiredSessions() {
        log.info("[start] SessaoCloserService - closeAllExpiredSessions");
        List<Sessao> sessoesAbertas = sessaoRepository.findAllByStatusWithVotos(SessaoStatus.ABERTO);
        sessoesAbertas.forEach(sessao ->{
            sessao.closeSessaoIfExpired();

            if(sessao.isSessaoClosed()) {
                sessaoRepository.save(sessao);
                snsNotificationService.enviaNotificacaoSns(UUID.randomUUID().toString(),
                        new SessaoResponseEmailDto(sessao),
                        topicSns);
            }

        });

        log.info("[end] SessaoCloserService - closeAllExpiredSessions");

    }
}
