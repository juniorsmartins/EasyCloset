package br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.request;

import br.com.devvader.EasyCloset.camada_de_recursos.entidades_persistidas.IAuditoria;
import br.com.devvader.EasyCloset.camada_de_recursos.entidades_persistidas.Contato;
import br.com.devvader.EasyCloset.camada_de_recursos.entidades_persistidas.Endereco;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public final class PessoaDtoEntradaListar {

    private String pessoaId;
    private String nome;
    private String sobrenome;
    private String cpf;

    private Contato contato = null;
    private Endereco endereco = null;
    private IAuditoria IAuditoria = null;
}
