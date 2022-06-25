package br.com.devvader.EasyCloset.camada_de_recursos.entidades_persistidas;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

public interface Auditoria {

    @CreationTimestamp
    LocalDateTime dataCriacao = null;
    @UpdateTimestamp
    LocalDateTime dataUltimaAtualizacao = null;
}
