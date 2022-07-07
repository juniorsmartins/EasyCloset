package br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public final class ContatoDtoSaida
{
    private String celular;
    private String email;
}
