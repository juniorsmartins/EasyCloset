package br.com.devvader.EasyCloset.camada_de_recursos.entidades_persistidas;

import br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.request.ContatoDtoEntrada;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "contatos")
@Data
@NoArgsConstructor
public final class Contato extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "pessoa_id")
    private Long pessoaId;
    @Column(name = "celular", length = 25, nullable = false)
    private String celular;
    @Column(name = "email", length = 100, nullable = false)
    private String email;

    @OneToOne
    @MapsId
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;

    // ---------- CONSTRUTORES ---------- //
    public Contato(ContatoDtoEntrada contatoDtoEntrada) {
        setCelular(contatoDtoEntrada.getCelular());
        setEmail(contatoDtoEntrada.getEmail());
    }
}
