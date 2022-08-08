package br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.request;

import br.com.devvader.EasyCloset.camada_de_recursos.entidades_persistidas.roupa.enuns.CoresEnum;
import br.com.devvader.EasyCloset.camada_de_recursos.entidades_persistidas.roupa.enuns.TamanhoEnum;
import br.com.devvader.EasyCloset.camada_de_recursos.entidades_persistidas.roupa.enuns.TecidoEnum;
import br.com.devvader.EasyCloset.camada_de_recursos.entidades_persistidas.roupa.enuns.TipoPecaEnum;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.Valid;
import javax.validation.constraints.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public final class RoupaDtoEntrada {

    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoPecaEnum tipoPeca;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TecidoEnum tecido;

    @NotNull
    @Enumerated(EnumType.STRING)
    private CoresEnum corPrincipal;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TamanhoEnum tamanho;

    @NotNull
    @Valid
    private CompraDtoEntrada compra;

    @NotNull
    @Positive
    private Long pessoaId;
}
