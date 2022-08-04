package br.com.devvader.EasyCloset.camada_de_recursos.entidades_persistidas.roupa;

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
    private String tipoPeca;
    @Column(name = "tecido")
    private String tecido;
    @Column(name = "cor_principal")
    private String corPrincipal;
    @Column(name = "tamanho")
    private String tamanho;

    @OneToOne(mappedBy = "roupa")
    private Compra compra;

}
