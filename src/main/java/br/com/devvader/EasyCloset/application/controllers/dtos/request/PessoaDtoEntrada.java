package br.com.devvader.EasyCloset.application.controllers.dtos.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public final class PessoaDtoEntrada {

    // ---------- ATRIBUTOS DE INSTÂNCIA ---------- //
    private String nome;
    private String sobrenome;
    private String cpf;

    private ContatoDtoEntrada contato;
    private EnderecoDtoEntrada endereco;
}
