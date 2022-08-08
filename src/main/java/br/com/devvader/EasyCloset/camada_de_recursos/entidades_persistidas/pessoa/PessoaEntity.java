package br.com.devvader.EasyCloset.camada_de_recursos.entidades_persistidas.pessoa;

import br.com.devvader.EasyCloset.camada_de_recursos.entidades_persistidas.AbstractAuditingEntity;
import br.com.devvader.EasyCloset.camada_de_recursos.entidades_persistidas.pessoa.composicao.ContatoEntity;
import br.com.devvader.EasyCloset.camada_de_recursos.entidades_persistidas.pessoa.composicao.EnderecoEntity;
import br.com.devvader.EasyCloset.camada_de_recursos.entidades_persistidas.roupa.RoupaEntity;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "PESSOAS")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public final class PessoaEntity extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long pessoaId;

    @Column(name = "nome", length = 25, nullable = false)
    private String nome;

    @Column(name = "sobrenome", length = 40, nullable = false)
    private String sobrenome;

    @Column(name = "cpf", length = 14, unique = true, nullable = false)
    private String cpf;

    @OneToOne(mappedBy = "pessoa", cascade = {CascadeType.ALL}, orphanRemoval = true, fetch = FetchType.LAZY)
    private ContatoEntity contato;

    @OneToOne(mappedBy = "pessoa", cascade = {CascadeType.ALL}, orphanRemoval = true, fetch = FetchType.LAZY)
    private EnderecoEntity endereco;

    @OneToMany(mappedBy = "pessoa", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private List<RoupaEntity> roupas;
}
