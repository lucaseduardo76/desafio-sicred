package com.sessao.voting.voto.dto;

import com.sessao.voting.voto.domain.VotoEnum;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class VotoRequestDto {
    
    @NotEmpty(message = "associadoId cannot be empty")
    private Long associadoId;
    @NotNull(message = "Voto cannot be null")
    private VotoEnum voto;
    @NotEmpty(message = "sessaoId cannot be empty")
    private String sessaoId;

}
