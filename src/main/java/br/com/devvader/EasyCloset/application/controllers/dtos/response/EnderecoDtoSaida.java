package br.com.devvader.EasyCloset.application.controllers.dtos.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public final class EnderecoDtoSaida {

    // ---------- ATRIBUTOS DE INSTÂNCIA ---------- //
    private Long pessoaId;
    private String cep;
    private String estado;
    private String cidade;
    private String bairro;
    private String logradouro;
    private int numero;
    private String complemento;
}
