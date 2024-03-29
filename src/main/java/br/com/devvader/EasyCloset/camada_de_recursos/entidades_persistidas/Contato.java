package br.com.devvader.EasyCloset.camada_de_recursos.entidades_persistidas;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "contatos")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public final class Contato extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contato_id", nullable = false, updatable = false)
    private Long contatoId;

    @Column(name = "celular", length = 25, nullable = false)
    private String celular;

    @Column(name = "email", length = 100, nullable = false)
    private String email;

    @OneToOne
    @JoinColumn(name = "pessoa_id")
    private PessoaEntity pessoa;
}
