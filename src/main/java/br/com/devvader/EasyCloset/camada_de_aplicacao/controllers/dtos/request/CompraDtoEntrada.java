package br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.request;

import br.com.devvader.EasyCloset.camada_de_recursos.entidades_persistidas.roupa.enuns.FormaPgtoEnum;
import br.com.devvader.EasyCloset.camada_de_recursos.entidades_persistidas.roupa.enuns.TipoPgtoEnum;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public final class CompraDtoEntrada {

    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoPgtoEnum tipoPgto;

    @NotNull
    @Enumerated(EnumType.STRING)
    private FormaPgtoEnum formaPgto;

    @NotNull
    @Positive
    private BigDecimal preco;

    @NotNull
    private LocalDate dataCompra;
}
