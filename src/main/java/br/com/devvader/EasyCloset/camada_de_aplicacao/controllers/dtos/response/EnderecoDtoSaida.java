package br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.response;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public final class EnderecoDtoSaida
{
    private String cep;
    private String estado;
    private String cidade;
    private String bairro;
    private String logradouro;
    private int numero;
    private String complemento;
}
