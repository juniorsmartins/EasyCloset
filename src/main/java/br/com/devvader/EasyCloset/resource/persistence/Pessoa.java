package br.com.devvader.EasyCloset.resource.persistence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "pessoas")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
public final class Pessoa implements Serializable {

    // ---------- ATRIBUTOS DE CLASSE ---------- //
    private static final long serialVersionUID = 1L;

    // ---------- ATRIBUTOS DE INSTÂNCIA ---------- //
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pessoaId;
    @Column(name = "nome", length = 25, nullable = false)
    private String nome;
    @Column(name = "sobrenome", length = 40, nullable = false)
    private String sobrenome;
    @Column(name = "cpf", length = 14, unique = true, nullable = false)
    private String cpf;

    @OneToOne(mappedBy = "pessoa", cascade = {CascadeType.ALL})
    @PrimaryKeyJoinColumn
    private Contato contato;
    @OneToOne(mappedBy = "pessoa", cascade = {CascadeType.ALL})
    @PrimaryKeyJoinColumn
    private Endereco endereco;
}
