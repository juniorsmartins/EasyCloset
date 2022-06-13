package br.com.devvader.EasyCloset.camada_de_dominio.entidades_nao_persistidas.tratamento_excecoes;

public class ValidacaoException extends RuntimeException {

    // ---------- ATRIBUTOS DE CLASSE ---------- //
    private static final long serialVersionUID = 1L;

    // ---------- CONSTRUTORES ---------- //
    public ValidacaoException(String mensagem) {
        super(mensagem);
    }
}
