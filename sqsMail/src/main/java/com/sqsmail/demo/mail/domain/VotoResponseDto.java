package com.sqsmail.demo.mail.domain;



import lombok.Data;

import java.time.LocalDateTime;

@Data
public class VotoResponseDto {
    private VotoEnum voto;
    private LocalDateTime dataHoraVoto;


}
