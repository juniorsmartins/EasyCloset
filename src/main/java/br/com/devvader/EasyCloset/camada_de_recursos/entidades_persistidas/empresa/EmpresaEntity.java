package br.com.devvader.EasyCloset.camada_de_recursos.entidades_persistidas.empresa;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "EMPRESAS")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public final class EmpresaEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "razao_social")
    private String razaoSocial;

    @Column(name = "nome_fantasia")
    private String nomeFantasia;

    @Column(name = "cnpj")
    private String cnpj;

    @Column(name = "fone")
    private String fone;

    @Column(name = "email")
    private String email;
}
