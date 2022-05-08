package br.com.devvader.EasyCloset.resource.persistence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "pessoas")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Pessoa implements Serializable {

    // ---------- MÉTODOS DE CLASSE ---------- //
    private static final long serialVersionUID = 1L;

    // ---------- MÉTODOS DE INSTÂNCIA ---------- //
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pessoaId;
    @Column(name = "nome", length = 25, nullable = false)
    private String nome;
    @Column(name = "sobrenome", length = 40, nullable = false)
    private String sobrenome;
    @Column(name = "cpf", unique = true, nullable = false)
    private String cpf;
}
