package br.com.devvader.EasyCloset.camada_de_recursos.persistence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "enderecos")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
public final class Endereco implements Serializable {

    // ---------- ATRIBUTOS DE CLASSE ---------- //
    private static final long serialVersionUID = 1L;

    // ---------- ATRIBUTOS DE INSTÂNCIA ---------- //
    @Id
    @Column(name = "pessoa_id")
    private Long pessoaId;

    @Column(name = "cep", length = 9, nullable = false)
    private String cep;
    @Column(name = "estado", length = 50, nullable = false)
    private String estado;
    @Column(name = "cidade", length = 50, nullable = false)
    private String cidade;
    @Column(name = "bairro", length = 50, nullable = false)
    private String bairro;
    @Column(name = "logradouro", length = 50, nullable = false)
    private String logradouro;
    @Column(name = "numero")
    private int numero;
    @Lob
    @Column(name = "complemento")
    private String complemento;

    @OneToOne
    @MapsId
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;
}
