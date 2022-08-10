package br.com.devvader.EasyCloset.camada_de_dominio.portas_de_servicos;

public interface IJasperService {

    void adicionarParametrosNoMapa(String key, Object value);
    byte[] exportarPDF(String code);
}
