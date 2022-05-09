package br.com.devvader.EasyCloset.application.controllers.dtos.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public final class ContatoDtoSaida {

    // ---------- ATRIBUTOS DE INSTÂNCIA ---------- //
    private Long pessoaId;
    private String celular;
    private String email;
}
