package br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.response;

import br.com.devvader.EasyCloset.camada_de_recursos.entidades_persistidas.Contato;
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
