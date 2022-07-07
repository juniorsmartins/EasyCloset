package br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
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
    private LocalDateTime dataDeCriacao;
    private LocalDateTime dataDaUltimaModificacao;
}
