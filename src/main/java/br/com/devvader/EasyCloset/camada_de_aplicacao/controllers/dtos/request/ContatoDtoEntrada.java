package br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public final class ContatoDtoEntrada {

    // ---------- ATRIBUTOS DE INSTÂNCIA ---------- //
    @NotNull(message = "{campo.celular.obrigatorio}")
    @NotEmpty(message = "{campo.celular.obrigatorio}")
    @Length(min = 8, max = 15, message = "{campo.celular.tamanho-limite}")
    private String celular;
    @NotNull(message = "{}")
    @NotEmpty(message = "{}")
    @Email(message = "{}")
    private String email;
}
