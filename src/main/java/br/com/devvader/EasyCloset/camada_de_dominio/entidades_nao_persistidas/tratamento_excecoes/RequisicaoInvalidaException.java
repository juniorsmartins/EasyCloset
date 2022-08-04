package br.com.devvader.EasyCloset.camada_de_dominio.entidades_nao_persistidas.tratamento_excecoes;

public final class RequisicaoInvalidaException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public RequisicaoInvalidaException(String mensagem) {
        super(mensagem);
    }
}
