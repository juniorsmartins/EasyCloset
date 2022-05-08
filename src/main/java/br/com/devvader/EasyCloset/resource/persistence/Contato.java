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
    @Column(name = "celular")
    private String celular;
    @Column(name = "email")
    private String email;
}
