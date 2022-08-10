package br.com.devvader.EasyCloset.camada_de_dominio.implementacoes_de_servicos;

import br.com.devvader.EasyCloset.camada_de_dominio.entidades_nao_persistidas.tratamento_excecoes.MensagensPadronizadas;
import br.com.devvader.EasyCloset.camada_de_dominio.entidades_nao_persistidas.tratamento_excecoes.RecursoNaoEncontradoException;
import br.com.devvader.EasyCloset.camada_de_dominio.entidades_nao_persistidas.tratamento_excecoes.RegraDeNegocioException;
import br.com.devvader.EasyCloset.camada_de_dominio.entidades_nao_persistidas.tratamento_excecoes.RequisicaoInvalidaException;
import br.com.devvader.EasyCloset.camada_de_dominio.portas_de_servicos.IJasperService;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
@Slf4j
public final class JasperServiceImpl implements IJasperService {

    private static final String JASPER_DIRETORIO = "classpath:jasper/";
    private static final String JASPER_PREFIXO = "roupas-";
    private static final String JASPER_SUFIXO = ".jasper";

    @Autowired
    private Connection conexaoComDatabase;

    private Map<String, Object> mapaDeParametros = new LinkedHashMap<>();

    @Override
    public void adicionarParametrosNoMapa(String key, Object value) {
        mapaDeParametros.put(key, value);
    }

    @Override
    public byte[] exportarPDF(String code) {
        byte[] bytes = null;
        try {
            File arquivo = ResourceUtils.getFile(JASPER_DIRETORIO.concat(JASPER_PREFIXO).concat(JASPER_SUFIXO)); // busca o arquivo pelo classpath
            JasperPrint imprimir = JasperFillManager.fillReport(arquivo.getAbsolutePath(), mapaDeParametros,
                    conexaoComDatabase);
            bytes = JasperExportManager.exportReportToPdf(imprimir);
        } catch (FileNotFoundException fnfe) {
            log.error(fnfe.getMessage(), fnfe.getCause(), fnfe.getStackTrace());
            throw new RequisicaoInvalidaException(MensagensPadronizadas.ARQUIVO_NAO_ENCONTRADO);
        } catch (JRException jre) {
            log.error(jre.getMessage(), jre.getCause(), jre.getStackTrace());
            throw new RequisicaoInvalidaException(MensagensPadronizadas.REQUISICAO_INVALIDA);
        }
        return bytes;
    }
}
