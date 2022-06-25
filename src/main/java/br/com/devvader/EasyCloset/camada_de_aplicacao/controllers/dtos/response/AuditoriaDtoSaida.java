package br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.response;

import br.com.devvader.EasyCloset.camada_de_recursos.entidades_persistidas.IAuditoria;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public final class AuditoriaDtoSaida {

    private LocalDateTime dataCriacao;
    private LocalDateTime dataUltimaAtualizacao;

    public AuditoriaDtoSaida(IAuditoria IAuditoria) {
        setDataCriacao(IAuditoria.getDataCriacao());
        setDataUltimaAtualizacao(IAuditoria.getDataUltimaAtualizacao());
    }
}
