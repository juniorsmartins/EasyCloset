package br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@NoArgsConstructor
public final class ContatoDtoSaida
{
    private String celular;
    private String email;
    private LocalDateTime dataDeCriacao;
    private LocalDateTime dataDaUltimaModificacao;
}
