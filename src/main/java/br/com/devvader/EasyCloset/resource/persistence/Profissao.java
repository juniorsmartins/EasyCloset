package br.com.devvader.EasyCloset.resource.persistence;

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
public final class Profissao implements Serializable {

    // ---------- ATRIBUTOS DE CLASSE ---------- //
    private static final long serialVersionUID = 1L;

    // ---------- ATRIBUTOS DE INSTÂNCIA ---------- //
    @Id
    @OneToOne()
    private Pessoa pessoa;

    @Column(name = "cargo", length = 100, nullable = false)
    private String cargo;
    @Column(name = "nivel", nullable = false)
    private String nivel;
    @Column(name = "salario", scale = 2, nullable = false)
    private BigDecimal salario;
}
