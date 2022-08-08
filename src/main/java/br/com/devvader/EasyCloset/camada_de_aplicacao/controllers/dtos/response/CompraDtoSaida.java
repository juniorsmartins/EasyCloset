package br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.response;

import br.com.devvader.EasyCloset.camada_de_recursos.entidades_persistidas.roupa.RoupaEntity;
import br.com.devvader.EasyCloset.camada_de_recursos.entidades_persistidas.roupa.enuns.FormaPgtoEnum;
import br.com.devvader.EasyCloset.camada_de_recursos.entidades_persistidas.roupa.enuns.TipoPgtoEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class CompraDtoSaida {

    private TipoPgtoEnum tipoPgto;
    private FormaPgtoEnum formaPgto;
    private BigDecimal preco;
    private LocalDate dataCompra;
    private Long roupaId;
}
