package br.com.devvader.EasyCloset.camada_de_recursos.entidades_persistidas;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractAuditingEntity {

    @CreatedDate
    private LocalDateTime dataDeCriacao;

    @LastModifiedDate
    private LocalDateTime dataDaUltimaModificacao;
}
