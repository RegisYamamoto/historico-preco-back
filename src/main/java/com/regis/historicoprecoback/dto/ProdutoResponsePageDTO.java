package com.regis.historicoprecoback.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProdutoResponsePageDTO {

    private Integer page = 0;
    private Integer size = 0;
    private Integer totalPages = 0;
    private List<ProdutoResponseDTO> produtosResponseDto= new ArrayList<>();

}
