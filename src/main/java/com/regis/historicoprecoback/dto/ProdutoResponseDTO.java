package com.regis.historicoprecoback.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Data
public class ProdutoResponseDTO {

    private String id = "";
    private String nome = "";
    private String descricao = "";
    private String marca = "";
    private LocalDateTime dataCadastro = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));
    private LocalDateTime dataUltAtualizacao = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));
    private List<PrecoResponseDTO> precos = new ArrayList<>();

}
