package com.regis.historicoprecoback.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Data
public class PrecoResponseDTO {

    private long id = 0L;
    private BigDecimal preco = new BigDecimal(0.0);
    private LocalDateTime dataConsulta = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));
    private String lojaConsultadada = "";

}
