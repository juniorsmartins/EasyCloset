package br.com.devvader.EasyCloset.camada_de_recursos.entidades_persistidas;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "pessoas")
@NoArgsConstructor
@Data
public final class Pessoa implements Serializable {

    // ---------- ATRIBUTOS DE CLASSE ---------- //
    private static final long serialVersionUID = 1L;

    // ---------- ATRIBUTOS DE INSTÂNCIA ---------- //
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pessoa_id")
    private Long pessoaId;
    @Column(name = "nome", length = 25, nullable = false)
    private String nome;
    @Column(name = "sobrenome", length = 40, nullable = false)
    private String sobrenome;
    @Column(name = "cpf", length = 14, unique = true, nullable = false)
    private String cpf;

    @OneToOne(mappedBy = "pessoa", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER, orphanRemoval = true, optional = false)
    @PrimaryKeyJoinColumn
    private Contato contato;
    @OneToOne(mappedBy = "pessoa", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER, orphanRemoval = true, optional = false)
    @PrimaryKeyJoinColumn
    private Endereco endereco;
}
