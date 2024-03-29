package br.com.devvader.EasyCloset.camada_de_recursos.entidades_persistidas.roupa;

import br.com.devvader.EasyCloset.camada_de_recursos.entidades_persistidas.roupa.enuns.FormaPgtoEnum;
import br.com.devvader.EasyCloset.camada_de_recursos.entidades_persistidas.roupa.enuns.TipoPgtoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "COMPRAS")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public final class CompraEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "tipo_pgto")
    @Enumerated(EnumType.STRING)
    private TipoPgtoEnum tipoPgto;
    @Column(name = "forma_pgto")
    @Enumerated(EnumType.STRING)
    private FormaPgtoEnum formaPgto;
    @Column(name = "preco")
    private BigDecimal preco;
    @Column(name = "data_compra")
    private LocalDate dataCompra;

    @OneToOne
    private RoupaEntity roupa;
}
