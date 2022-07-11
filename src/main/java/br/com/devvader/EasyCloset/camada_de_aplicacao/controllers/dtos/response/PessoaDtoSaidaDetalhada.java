package br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public final class PessoaDtoSaidaDetalhada
{
    private Long pessoaId;
    private String nome;
    private String sobrenome;
    private String cpf;

    private ContatoDtoSaida contato;
    private EnderecoDtoSaida endereco;

    private LocalDateTime dataDeCriacao;
    private LocalDateTime dataDaUltimaModificacao;
}
