package br.com.devvader.EasyCloset.application.controllers.dtos.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Data
public final class ContatoDtoEntrada {

    // ---------- ATRIBUTOS DE INSTÂNCIA ---------- //
    @NotBlank
    private String celular;
    @Email
    private String email;
}
