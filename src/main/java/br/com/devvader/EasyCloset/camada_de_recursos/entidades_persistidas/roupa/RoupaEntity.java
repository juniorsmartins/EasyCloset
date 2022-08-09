package br.com.devvader.EasyCloset.camada_de_recursos.entidades_persistidas.roupa;

import br.com.devvader.EasyCloset.camada_de_recursos.entidades_persistidas.pessoa.PessoaEntity;
import br.com.devvader.EasyCloset.camada_de_recursos.entidades_persistidas.roupa.composicao.CompraEntity;
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
    @Column(name = "roupa_id", nullable = false, updatable = false)
    private Long roupaId;

    @Column(name = "tipo_peca", length = 50)
    @Enumerated(EnumType.STRING)
    private TipoPecaEnum tipoPeca;

    @Column(name = "tecido", length = 50)
    @Enumerated(EnumType.STRING)
    private TecidoEnum tecido;

    @Column(name = "cor_principal", length = 50)
    @Enumerated(EnumType.STRING)
    private CoresEnum corPrincipal;

    @Column(name = "tamanho", length = 50)
    @Enumerated(EnumType.STRING)
    private TamanhoEnum tamanho;

    @OneToOne(mappedBy = "roupa", cascade = {CascadeType.ALL}, orphanRemoval = true, fetch = FetchType.LAZY)
    private CompraEntity compra;

    @ManyToOne
    @JoinColumn(name = "id_pessoa", nullable = false, updatable = false)
    private PessoaEntity pessoa;
}
