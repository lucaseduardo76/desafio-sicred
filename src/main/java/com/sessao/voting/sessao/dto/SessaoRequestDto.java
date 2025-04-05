package com.sessao.voting.sessao.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SessaoRequestDto {
    private Long duracaoMinutos;

    @NotEmpty(message = "pautaId cannot be empty")
    private String pautaId;
}
