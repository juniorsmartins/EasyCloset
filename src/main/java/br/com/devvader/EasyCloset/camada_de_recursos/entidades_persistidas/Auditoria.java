package br.com.devvader.EasyCloset.camada_de_recursos.entidades_persistidas;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "auditorias")
@Data
@NoArgsConstructor
public final class Auditoria implements Serializable {

    private static final Long serialVersionUID = 1L;

    @Id
    @Column(name = "pessoa_id")
    private Long pessoaId;
    @Column(name = "data_criacao", nullable = false)
    private LocalDateTime dataCriacao;
    @Column(name = "data_ultima_atualizacao")
    private LocalDateTime dataUltimaAtualizacao;

    @OneToOne
    @MapsId
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;
}
