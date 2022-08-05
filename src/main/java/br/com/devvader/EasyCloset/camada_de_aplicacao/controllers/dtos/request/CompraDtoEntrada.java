package br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.request;

import br.com.devvader.EasyCloset.camada_de_recursos.entidades_persistidas.roupa.RoupaEntity;
import br.com.devvader.EasyCloset.camada_de_recursos.entidades_persistidas.roupa.enuns.FormaPgtoEnum;
import br.com.devvader.EasyCloset.camada_de_recursos.entidades_persistidas.roupa.enuns.TipoPgtoEnum;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public final class CompraDtoEntrada {

    @NotBlank
    @Enumerated(EnumType.STRING)
    private TipoPgtoEnum tipoPgto;

    @NotBlank
    @Enumerated(EnumType.STRING)
    private FormaPgtoEnum formaPgto;

    @NotBlank
    private BigDecimal preco;

    @NotBlank
    private LocalDate dataCompra;

    @NotBlank
    private RoupaEntity roupa;
}
