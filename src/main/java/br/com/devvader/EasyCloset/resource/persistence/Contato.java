package br.com.devvader.EasyCloset.resource.persistence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "contatos")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
public final class Contato implements Serializable
{
    // ---------- ATRIBUTOS DE CLASSE ---------- //
    private static final long serialVersionUID = 1L;

    // ---------- ATRIBUTOS DE INSTÂNCIA ---------- //
    @Id
    @Column(name = "pessoa_id")
    private Long pessoaId;

    @Column(name = "celular", length = 25)
    private String celular;
    @Column(name = "email", length = 100)
    private String email;

    @OneToOne
    @MapsId
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;
}
