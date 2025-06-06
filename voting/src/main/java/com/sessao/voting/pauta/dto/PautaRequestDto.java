package com.sessao.voting.pauta.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PautaRequestDto {

    @NotEmpty(message = "Descricao cannot be empty")
    private String descricao;
    @Email
    @NotEmpty(message = "Email cannot be empty")
    private String email;

}
