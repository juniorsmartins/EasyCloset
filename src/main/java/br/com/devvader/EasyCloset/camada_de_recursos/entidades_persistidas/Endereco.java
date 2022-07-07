package br.com.devvader.EasyCloset.camada_de_recursos.entidades_persistidas;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "enderecos")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public final class Endereco extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "pessoa_id")
    private Long pessoaId;

    @Column(name = "cep", length = 9, nullable = false)
    private String cep;

    @Column(name = "estado", length = 50, nullable = false)
    private String estado;

    @Column(name = "cidade", length = 50, nullable = false)
    private String cidade;

    @Column(name = "bairro", length = 50, nullable = false)
    private String bairro;

    @Column(name = "logradouro", length = 50, nullable = false)
    private String logradouro;

    @Column(name = "numero")
    private int numero;

    @Column(name = "complemento")
    private String complemento;

    @OneToOne
    @MapsId
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;
}
