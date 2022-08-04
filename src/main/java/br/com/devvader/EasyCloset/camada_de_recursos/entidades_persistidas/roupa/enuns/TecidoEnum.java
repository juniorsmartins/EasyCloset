package br.com.devvader.EasyCloset.camada_de_recursos.entidades_persistidas.roupa.enuns;

public enum TecidoEnum {

    TRICOLINE("Tricoline"),
    MALHA("Malha"),
    CETIM("Cetim"),
    CANVAS("Canvas"),
    CÓRDOBA("Córdoba"),
    GABARDINE("Gabardine"),
    MICROFIBRA("Microfibra"),
    MOUSSELINE("Mousseline"),
    OXFORD("Oxford"),
    VELLUM("Vellum"),
    COURO("Couro"),
    FELTRO("Feltro"),
    ALGODÃO("Algodão"),
    SEDA("Seda"),
    LINHO("Linho"),
    TENCEL("Tencel"),
    VISCOSE("Viscose"),
    VISCOLAYCRA("Viscolaycra"),
    CAMBRAIA("Cambraia"),
    LAISE("Laise"),
    RENDA("Renda"),
    LÃ("Lã"),
    BRIM("Brim"),
    SARJA("Sarja"),
    CREPE("Crepe");

    private String valor;

    TecidoEnum(String valor) {
        this.valor = valor;
    }

    private String getValor() {
        return valor;
    }
}
