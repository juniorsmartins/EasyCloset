package br.com.devvader.EasyCloset.application.controllers.dtos.response;

import br.com.devvader.EasyCloset.resource.persistence.Contato;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public final class ContatoDtoSaida {

    // ---------- ATRIBUTOS DE INSTÂNCIA ---------- //
    private Long pessoaId;
    private String celular;
    private String email;

    // ---------- CONSTRUTORES ---------- //
    public ContatoDtoSaida(Contato contato) {
        setPessoaId(contato.getPessoaId());
        setCelular(contato.getCelular());
        setEmail(contato.getEmail());
    }
}
