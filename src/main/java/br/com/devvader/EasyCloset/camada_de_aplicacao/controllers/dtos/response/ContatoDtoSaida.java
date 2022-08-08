package br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.response;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public final class ContatoDtoSaida
{
    private Long contatoId;
    private String celular;
    private String email;
}
