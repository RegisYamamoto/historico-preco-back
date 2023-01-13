package com.regis.historicoprecoback.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PrecoRequestDTO {

    private BigDecimal preco = new BigDecimal(0);
    private String lojaConsultada = "";

}
