package br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.response;

import br.com.devvader.EasyCloset.camada_de_recursos.entidades_persistidas.roupa.CompraEntity;
import br.com.devvader.EasyCloset.camada_de_recursos.entidades_persistidas.roupa.enuns.CoresEnum;
import br.com.devvader.EasyCloset.camada_de_recursos.entidades_persistidas.roupa.enuns.TamanhoEnum;
import br.com.devvader.EasyCloset.camada_de_recursos.entidades_persistidas.roupa.enuns.TecidoEnum;
import br.com.devvader.EasyCloset.camada_de_recursos.entidades_persistidas.roupa.enuns.TipoPecaEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
public final class RoupaDtoSaida {

    private TipoPecaEnum tipoPeca;
    private TecidoEnum tecido;
    private CoresEnum corPrincipal;
    private TamanhoEnum tamanho;
    private CompraEntity compraEntity;
    private Long pessoaId;
}
