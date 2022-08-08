package br.com.devvader.EasyCloset.camada_de_recursos.entidades_persistidas.roupa;

import br.com.devvader.EasyCloset.camada_de_recursos.entidades_persistidas.pessoa.PessoaEntity;
import br.com.devvader.EasyCloset.camada_de_recursos.entidades_persistidas.roupa.enuns.CoresEnum;
import br.com.devvader.EasyCloset.camada_de_recursos.entidades_persistidas.roupa.enuns.TamanhoEnum;
import br.com.devvader.EasyCloset.camada_de_recursos.entidades_persistidas.roupa.enuns.TecidoEnum;
import br.com.devvader.EasyCloset.camada_de_recursos.entidades_persistidas.roupa.enuns.TipoPecaEnum;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ROUPAS")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public final class RoupaEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long roupaId;

    @Column(name = "tipo_peca")
    @Enumerated(EnumType.STRING)
    private TipoPecaEnum tipoPeca;

    @Column(name = "tecido")
    @Enumerated(EnumType.STRING)
    private TecidoEnum tecido;

    @Column(name = "cor_principal")
    @Enumerated(EnumType.STRING)
    private CoresEnum corPrincipal;

    @Column(name = "tamanho")
    @Enumerated(EnumType.STRING)
    private TamanhoEnum tamanho;

    @OneToOne(mappedBy = "roupaId", cascade = {CascadeType.ALL}, orphanRemoval = true, fetch = FetchType.LAZY)
    private CompraEntity compraId;

    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    private PessoaEntity pessoaId;
}
