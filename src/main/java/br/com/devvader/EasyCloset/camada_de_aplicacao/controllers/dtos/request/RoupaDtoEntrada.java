package br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.request;

import br.com.devvader.EasyCloset.camada_de_recursos.entidades_persistidas.roupa.CompraEntity;
import br.com.devvader.EasyCloset.camada_de_recursos.entidades_persistidas.roupa.enuns.CoresEnum;
import br.com.devvader.EasyCloset.camada_de_recursos.entidades_persistidas.roupa.enuns.TamanhoEnum;
import br.com.devvader.EasyCloset.camada_de_recursos.entidades_persistidas.roupa.enuns.TecidoEnum;
import br.com.devvader.EasyCloset.camada_de_recursos.entidades_persistidas.roupa.enuns.TipoPecaEnum;
import lombok.*;

import javax.validation.constraints.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public final class RoupaDtoEntrada {

    @NotNull
    @NotEmpty
    @PositiveOrZero
    private TipoPecaEnum tipoPeca;

    @NotNull
    @NotEmpty
    @PositiveOrZero
    private TecidoEnum tecido;

    @NotNull
    @NotEmpty
    @PositiveOrZero
    private CoresEnum corPrincipal;

    @NotNull
    @NotEmpty
    @PositiveOrZero
    private TamanhoEnum tamanho;

    // parei aqui - Falta construir CompraDtoEntrada e CompraDtoSaida e MapStruct deles.
    @NotBlank
    private CompraEntity compra;

    @NotNull
    @NotEmpty
    @Positive
    private Long pessoaId;
}
