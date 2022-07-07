package br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public final class PessoaDtoSaidaDetalhada
{
    private Long pessoaId;
    private String nome;
    private String sobrenome;
    private String cpf;
    private LocalDateTime dataDeCriacao;
    private LocalDateTime dataDaUltimaModificacao;

    private ContatoDtoSaida contato;
    private EnderecoDtoSaida endereco;
}
