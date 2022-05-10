package br.com.devvader.EasyCloset.application.controllers.errors;

public class ValidacaoException extends RuntimeException {

    // ---------- ATRIBUTOS DE CLASSE ---------- //
    private static final long serialVersionUID = 1L;

    // ---------- CONSTRUTORES ---------- //
    public ValidacaoException(String mensagem) {
        super(mensagem);
    }
}
