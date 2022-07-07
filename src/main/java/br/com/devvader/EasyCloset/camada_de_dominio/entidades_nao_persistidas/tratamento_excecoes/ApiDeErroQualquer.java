package br.com.devvader.EasyCloset.camada_de_dominio.entidades_nao_persistidas.tratamento_excecoes;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public final class ApiDeErroQualquer {

    private String status;
    private String mensagem;

    public ApiDeErroQualquer(String status, String mensagem) {
/*        setStatus(status);
        setMensagem(mensagem);*/
    }
}
