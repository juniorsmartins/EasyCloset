package br.com.devvader.EasyCloset.resource.persistence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
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
    @Column(name = "cep", nullable = false)
    private String cep;
    @Column(name = "estado", nullable = false)
    private String estado;
    @Column(name = "cidade", nullable = false)
    private String cidade;
    @Column(name = "bairro", nullable = false)
    private String bairro;
    @Column(name = "logradouro", nullable = false)
    private String logradouro;
    @Column(name = "numero")
    private int numero;
    @Column(name = "complemento")
    private String complemento;
}
