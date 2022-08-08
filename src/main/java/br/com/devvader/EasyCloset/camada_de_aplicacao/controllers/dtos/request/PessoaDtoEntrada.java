package br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.request;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public final class PessoaDtoEntrada {

    private Long id;

    @NotNull(message = "{campo.nome.obrigatorio}")
    @NotEmpty(message = "{campo.nome.obrigatorio}")
    @Length(min = 2, max = 50, message = "{campo.nome.tamanho-limite}")
    private String nome;

    @NotNull(message = "{campo.sobrenome.obrigatorio}")
    @NotEmpty(message = "{campo.sobrenome.obrigatorio}")
    @Length(min = 2, max = 100, message = "{campo.sobrenome.tamanho-limite}")
    private String sobrenome;

    @NotNull(message = "{campo.cpf.obrigatorio}")
    @NotEmpty(message = "{campo.cpf.obrigatorio}")
    @CPF(message = "{campo.cpf.valido}")
    private String cpf;

    @Valid
    @NotNull(message = "{campo.contato.obrigatorio}")
    private ContatoDtoEntrada contato;

    @Valid
    @NotNull(message = "{campo.endereco.obrigatorio}")
    private EnderecoDtoEntrada endereco;
}
