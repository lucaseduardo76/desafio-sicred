package com.sessao.voting.associado.dto;

import com.sessao.voting.feignClient.cpfValidation.domain.Situacao;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StatusCpf {
    private cpfStatus status;

    public StatusCpf(Situacao situacao) {
        if(situacao.getCodigo().equals("0")){
            this.status = cpfStatus.ABLE_TO_VOTE ;
        }else{
            this.status = cpfStatus.UNABLE_TO_VOTE;
        }
    }
}
