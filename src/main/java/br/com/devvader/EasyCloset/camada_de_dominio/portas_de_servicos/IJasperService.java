package br.com.devvader.EasyCloset.camada_de_dominio.portas_de_servicos;

import net.sf.jasperreports.engine.JasperReport;

import java.util.LinkedHashMap;
import java.util.Map;

public interface IJasperService {

    Map<String, Object> mapaDeParametros = new LinkedHashMap<>();

    void adicionarParametrosNoMapa(String key, Object value);
    JasperReport compilarArquivoJrxml(String arquivo);
}
