package com.regis.historicoprecoback.model;

import jakarta.persistence.*;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Data
@Entity
@Table(name = "preco")
public class Preco implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id = 0L;

    @NotNull
    @Column(name = "preco", nullable = false)
    private BigDecimal preco = new BigDecimal(0.0);

    @NotNull
    @Column(name = "data_consulta", nullable = false)
    private LocalDateTime dataConsulta = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));

    @NotNull
    @Column(name = "loja_consultada", nullable = false)
    private String lojaConsultadada = "";

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

}
