package br.com.devvader.EasyCloset.camada_de_recursos.entidades_persistidas.roupa.enuns;

public enum TipoPgtoEnum {

    A_VISTA("À vista"),
    A_PRAZO("A prazo");

    private String valor;

    TipoPgtoEnum(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
