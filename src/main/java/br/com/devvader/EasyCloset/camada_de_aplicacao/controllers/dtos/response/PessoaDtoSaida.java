package br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public final class PessoaDtoSaida
{
    private Long pessoaId;
    private String nome;
    private String sobrenome;
    private String cpf;

    private ContatoDtoSaida contato;
    private EnderecoDtoSaida endereco;
}
