package br.com.devvader.EasyCloset.camada_de_recursos.entidades_persistidas.roupa.enuns;

public enum TamanhoEnum {

    PP("PP"),
    P("P"),
    M("M"),
    G("G"),
    GG("GG"),
    XG("XG"),
    XGG("XGG"),
    EG("EG"),
    EGG("EGG");

    private String valor;

    TamanhoEnum(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
