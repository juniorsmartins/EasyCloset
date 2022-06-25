package br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public final class AuditoriaDtoSaida {

    private LocalDateTime dataCriacao;
    private LocalDateTime dataUltimaAtualizacao;
}
