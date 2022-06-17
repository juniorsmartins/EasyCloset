package br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public final class PessoaDtoEntradaListar {

    private String id;
    private String nome;
    private String sobrenome;
    private String cpf;
}
