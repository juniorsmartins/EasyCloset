package br.com.devvader.EasyCloset.application.controllers.dtos.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Data
public final class EnderecoDtoEntrada {

    // ---------- ATRIBUTOS DE INSTÂNCIA ---------- //
    private String cep;
    private String estado;
    private String cidade;
    private String bairro;
    private String logradouro;
    private int numero;
    private String complemento;
}
