package br.com.devvader.EasyCloset.application.controllers.dtos.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public final class ContatoDtoEntrada {

    // ---------- ATRIBUTOS DE INSTÂNCIA ---------- //
    private String celular;
    private String email;
}
