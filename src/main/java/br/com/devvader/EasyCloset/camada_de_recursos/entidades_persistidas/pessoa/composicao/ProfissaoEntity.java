package br.com.devvader.EasyCloset.camada_de_recursos.entidades_persistidas.pessoa.composicao;

import br.com.devvader.EasyCloset.camada_de_recursos.entidades_persistidas.pessoa.PessoaEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "profissoes")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
public final class ProfissaoEntity implements Serializable {

    // ---------- ATRIBUTOS DE CLASSE ---------- //
    private static final long serialVersionUID = 1L;

    // ---------- ATRIBUTOS DE INSTÂNCIA ---------- //
    @Id
    @OneToOne()
    private PessoaEntity pessoa;

    @Column(name = "cargo", length = 100, nullable = false)
    private String cargo;
    @Column(name = "nivel", nullable = false)
    private String nivel;
    @Column(name = "salario", scale = 2, nullable = false)
    private BigDecimal salario;
}
