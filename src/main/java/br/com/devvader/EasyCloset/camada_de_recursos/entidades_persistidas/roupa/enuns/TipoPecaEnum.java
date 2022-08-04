package br.com.devvader.EasyCloset.camada_de_recursos.entidades_persistidas.roupa.enuns;

public enum TipoPecaEnum {

    CHAPÉU("Chapéu"),
    BONÉ("Boné"),
    GORRO("Gorro"),
    GRAVATA("Gravata"),
    JAQUETA("Jaqueta"),
    BLUSA("Blusa"),
    BLAZER("Blazer"),
    SUÉTER("Suéter"),
    CASACO("Casaco"),
    CAMISETA("Camiseta"),
    CAMISA("Camisa"),
    CALÇA("Calça"),
    TERNO("Terno"),
    CINTO("Cinto"),
    SHORT("Short"),
    BERMUDA("Bermuda"),
    CALÇÃO("Calção"),
    CUECA("Cueca"),
    MEIA("Meia");

    private String valor;

    TipoPecaEnum(String valor) {
        this.valor = valor;
    }

    private String getValor() {
        return valor;
    }
}
