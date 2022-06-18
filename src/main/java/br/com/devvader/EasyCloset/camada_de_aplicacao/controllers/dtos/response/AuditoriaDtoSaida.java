package br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.response;

import br.com.devvader.EasyCloset.camada_de_recursos.entidades_persistidas.Auditoria;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public final class AuditoriaDtoSaida {

    private LocalDateTime dataCriacao;
    private LocalDateTime dataUltimaAtualizacao;

    public AuditoriaDtoSaida(Auditoria auditoria) {
        setDataCriacao(auditoria.getDataCriacao());
        setDataUltimaAtualizacao(auditoria.getDataUltimaAtualizacao());
    }
}
