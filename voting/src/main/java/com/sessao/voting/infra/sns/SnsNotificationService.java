package com.sessao.voting.infra.sns;


import io.awspring.cloud.sns.core.SnsNotification;
import io.awspring.cloud.sns.core.SnsOperations;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class SnsNotificationService {

    @Autowired
    private SnsOperations snsOperations;


    public <T> void enviaNotificacaoSns(String groupId, T payload, String topic) {
        log.info("[start] PublicadorNotificacaoInfraSNS - publicaMensagem");
        SnsNotification<T> notification = SnsNotification.<T>builder(payload)
//                .deduplicationId(UUID.randomUUID().toString())
//                .groupId(groupId)
                .build();
        log.debug("[sending] Request com valor \"{}\" para o tópico \"{}\"", payload.toString(), topic);
        snsOperations.sendNotification(topic, notification);
        log.debug("[finish] PublicadorNotificacaoInfraSNS - publicaMensagem");
    }
}
