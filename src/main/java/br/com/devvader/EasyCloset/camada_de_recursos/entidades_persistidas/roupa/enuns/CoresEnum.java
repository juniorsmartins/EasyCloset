package br.com.devvader.EasyCloset.camada_de_recursos.entidades_persistidas.roupa.enuns;

public enum CoresEnum {

    AZUL("Azul"),
    AMARELO("Amarelo"),
    BEGE("Bege"),
    BORDÔ("Bordô"),
    BRANCO("Branco"),
    BRONZE("Bronze"),
    CARAMELO("Caramelo"),
    CAQUI("Caqui"),
    CARMESIM("Carmesim"),
    CARMIM("Carmim"),
    CASTANHO("Castanho"),
    CIANO("Ciano"),
    COBRE("Cobre"),
    CREME("Creme"),
    DOURADO("Dourado"),
    ESCARLATE("Escarlate"),
    ESMERALDA("Esmeralda"),
    LARANJA("Laranja"),
    LILÁS("Lilás"),
    MAGENTA("Magenta"),
    MARFIM("Marfim"),
    MARROM("Marrom"),
    PRATA("Prata"),
    PRETO("Preto"),
    PÚRPURA("Púrpura"),
    ROSA("Rosa"),
    ROXO("Roxo"),
    SALMÃO("Salmão"),
    TURQUESA("Turquesa"),
    VERDE("Verde"),
    VERMELHO("Vermelho"),
    VIOLETA("Violeta");

    private String valor;

    CoresEnum(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
