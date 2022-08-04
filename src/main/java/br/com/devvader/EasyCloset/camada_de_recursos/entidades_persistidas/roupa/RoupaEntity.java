package br.com.devvader.EasyCloset.camada_de_recursos.entidades_persistidas.roupa;

import br.com.devvader.EasyCloset.camada_de_recursos.entidades_persistidas.PessoaEntity;
import br.com.devvader.EasyCloset.camada_de_recursos.entidades_persistidas.roupa.enuns.CoresEnum;
import br.com.devvader.EasyCloset.camada_de_recursos.entidades_persistidas.roupa.enuns.TamanhoEnum;
import br.com.devvader.EasyCloset.camada_de_recursos.entidades_persistidas.roupa.enuns.TecidoEnum;
import br.com.devvader.EasyCloset.camada_de_recursos.entidades_persistidas.roupa.enuns.TipoPecaEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ROUPAS")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public final class RoupaEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

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

    @OneToOne(mappedBy = "roupa", cascade = {CascadeType.ALL}, orphanRemoval = true, fetch = FetchType.LAZY)
    private CompraEntity compraEntity;

    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    private PessoaEntity pessoa;
}
