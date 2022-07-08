package br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public final class ContatoDtoEntrada {

    @NotNull(message = "{campo.celular.obrigatorio}")
    @NotEmpty(message = "{campo.celular.obrigatorio}")
    @Length(min = 8, max = 15, message = "{campo.celular.tamanho-limite}")
    private String celular;

    @NotNull(message = "{campo.email.obrigatorio}")
    @NotEmpty(message = "{campo.email.obrigatorio}")
    @Email(message = "{campo.email.valido}")
    private String email;
}
