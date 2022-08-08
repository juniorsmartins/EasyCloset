package br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.request;

import br.com.devvader.EasyCloset.camada_de_recursos.entidades_persistidas.AbstractAuditingEntity;
import br.com.devvader.EasyCloset.camada_de_recursos.entidades_persistidas.pessoa.composicao.ContatoEntity;
import br.com.devvader.EasyCloset.camada_de_recursos.entidades_persistidas.pessoa.composicao.EnderecoEntity;
import lombok.*;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public final class PessoaDtoEntradaListar {

    private String pessoaId;
    private String nome;
    private String sobrenome;
    private String cpf;

    private ContatoEntity contato = null;
    private EnderecoEntity endereco = null;
    private AbstractAuditingEntity AbstractAuditingEntity = null;
}
