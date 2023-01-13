package com.regis.historicoprecoback.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "produto")
public class Produto implements Serializable {

    @Id
    @Column(name = "id")
    private String id = "";

    @Column(name = "nome", nullable = false)
    private String nome = "";

    @Column(name = "descricao")
    private String descricao = "";

    @Column(name = "marca")
    private String marca = "";

    @Column(name = "data_cadastro", nullable = false)
    private LocalDateTime dataCadastro = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));

    @Column(name = "data_ult_atualizacao")
    private LocalDateTime dataUltAtualizacao = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));

    @OneToMany(mappedBy = "produto")
    private List<Preco> precos = new ArrayList<>();

}
