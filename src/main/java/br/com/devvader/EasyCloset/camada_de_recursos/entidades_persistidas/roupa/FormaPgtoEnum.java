package br.com.devvader.EasyCloset.camada_de_recursos.entidades_persistidas.roupa;

public enum FormaPgtoEnum {

    CARTAO("Cartão"),
    PIX("Pix"),
    BOLETO("Boleto"),
    DINHEIRO("Dinheiro");

    private String valor;

    FormaPgtoEnum(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
