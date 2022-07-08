package br.com.devvader.EasyCloset.camada_de_dominio.entidades_nao_persistidas.tratamento_excecoes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public final class ApiDeErroDeBeanValidation {

    private String status;
    private String mensagem;
    private String anotacao;
    private String nomeDoCampo;
}
