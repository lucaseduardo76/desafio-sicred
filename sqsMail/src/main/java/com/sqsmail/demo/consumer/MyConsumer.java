package com.sqsmail.demo.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sqsmail.demo.mail.domain.SessaoResultado;
import com.sqsmail.demo.mail.service.MailService;
import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
@Log4j2
public class MyConsumer {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MailService mailService;

    @SqsListener(value = "${spring.cloud.aws.sqs.name:mailPedidoSender}")
    public void receiveMessage(String message) {
        try {
            String internalMessage = objectMapper.readTree(message).get("Message").asText();

            // Agora, deserializamos o "Message" interno em um objeto PedidoInfo
            SessaoResultado pedidoInfo = objectMapper.readValue(internalMessage, SessaoResultado.class);

            // Agora você pode manipular a informação
            System.out.println(pedidoInfo);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

           String titulo = "Informação da sessão de voto";

            String mensagem =
                    "Nessa sessão tivemos um total de " + pedidoInfo.getContagem().getTotalVotos() + " voto(s)\n" +
                            "Criado no dia " + formatter.format(pedidoInfo.getDataHoraCriacao()) +
                            " às "+ timeFormatter.format(pedidoInfo.getDataHoraCriacao()) +"\n" +
                            "E finalizado no dia " + formatter.format(pedidoInfo.getDataHoraFim()) +
                            " às "+ timeFormatter.format(pedidoInfo.getDataHoraFim()) +"\n" +
                            "Total de votos: " + pedidoInfo.getContagem().getTotalVotos() + "\n" +
                            "Total de votos SIM: " + pedidoInfo.getContagem().getVotoSim() + "\n" +
                            "Total de votos NÃO: " + pedidoInfo.getContagem().getVotoNao();


            System.out.println(mailService.enviarEmailTexto(pedidoInfo.getPauta().getEmail(), titulo, mensagem));


        } catch (Exception e) {
            log.error("Erro ao processar a mensagem SQS", e);
        }
    }

}
