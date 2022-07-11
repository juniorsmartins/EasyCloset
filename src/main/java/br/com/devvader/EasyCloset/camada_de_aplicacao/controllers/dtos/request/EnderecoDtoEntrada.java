package br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.request;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public final class EnderecoDtoEntrada {

    @NotNull(message = "{campo.cep.obrigatorio}")
    @NotEmpty(message = "{campo.cep.obrigatorio}")
    private String cep;

    @NotNull(message = "{campo.estado.obrigatorio}")
    @NotEmpty(message = "{campo.estado.obrigatorio}")
    @Length(min = 2, max = 100, message = "{campo.estado.tamanho-limite}")
    private String estado;

    @NotNull(message = "{campo.cidade.obrigatorio}")
    @NotEmpty(message = "{campo.cidade.obrigatorio}")
    @Length(min = 2, max = 100, message = "{campo.cidade.tamanho-limite}")
    private String cidade;

    @NotNull(message = "{campo.bairro.obrigatorio}")
    @NotEmpty(message = "{campo.bairro.obrigatorio}")
    @Length(min = 2, max = 100, message = "{campo.bairro.tamanho-limite}")
    private String bairro;

    @NotNull(message = "{campo.logradouro.obrigatorio}")
    @NotEmpty(message = "{campo.logradouro.obrigatorio}")
    @Length(min = 2, max = 100, message = "{campo.logradouro.tamanho-limite}")
    private String logradouro;

    @NotNull(message = "{campo.numero.nao-nulo}")
    @PositiveOrZero(message = "{campo.numero.positivo-zero}")
    private int numero;

    private String complemento;
}
