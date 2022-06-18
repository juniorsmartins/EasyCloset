package br.com.devvader.EasyCloset.camada_de_aplicacao.controllers.dtos.response;

import br.com.devvader.EasyCloset.camada_de_recursos.entidades_persistidas.Contato;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public final class ContatoDtoSaida {

    private String celular;
    private String email;

    public ContatoDtoSaida(Contato contato) {
        setCelular(contato.getCelular());
        setEmail(contato.getEmail());
    }
}
