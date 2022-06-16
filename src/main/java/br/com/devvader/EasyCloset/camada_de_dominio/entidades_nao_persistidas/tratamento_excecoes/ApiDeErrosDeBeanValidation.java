package br.com.devvader.EasyCloset.camada_de_dominio.entidades_nao_persistidas.tratamento_excecoes;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public final class ApiDeErrosDeBeanValidation {

    private String status;
    private String mensagem;
    private String anotacao;
    private String nomeDoCampo;

    public ApiDeErrosDeBeanValidation(String status, String mensagem, String anotacao, String nomeDoCampo) {
        setStatus(status);
        setMensagem(mensagem);
        setAnotacao(anotacao);
        setNomeDoCampo(nomeDoCampo);
    }
}
