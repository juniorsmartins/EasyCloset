package br.com.devvader.EasyCloset.application.controllers.dtos.response;

import br.com.devvader.EasyCloset.application.controllers.dtos.request.ContatoDtoEntrada;
import br.com.devvader.EasyCloset.application.controllers.dtos.request.EnderecoDtoEntrada;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public final class PessoaDtoSaida {

    // ---------- ATRIBUTOS DE INSTÂNCIA ---------- //
    private Long pessoaId;
    private String nome;
    private String sobrenome;
    private String cpf;

    private ContatoDtoSaida contato;
    private EnderecoDtoSaida endereco;
}
