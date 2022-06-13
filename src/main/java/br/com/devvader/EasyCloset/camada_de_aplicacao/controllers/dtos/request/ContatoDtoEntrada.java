package br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Data
public final class ContatoDtoEntrada {

    // ---------- ATRIBUTOS DE INSTÂNCIA ---------- //
    @NotNull
    @NotEmpty
    private String celular;
    @NotNull
    @NotEmpty
    @Email
    private String email;
}
