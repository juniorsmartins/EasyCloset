package br.com.devvader.EasyCloset.application.controllers.dtos.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Data
public final class EnderecoDtoEntrada {

    // ---------- ATRIBUTOS DE INSTÂNCIA ---------- //
    @NotBlank
    private String cep;
    @NotBlank
    private String estado;
    @NotBlank
    private String cidade;
    @NotBlank
    private String bairro;
    @NotBlank
    private String logradouro;
    private int numero;
    private String complemento;
}
