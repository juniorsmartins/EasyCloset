package br.com.devvader.EasyCloset.camada_de_dominio.implementacoes_de_servicos;

import br.com.devvader.EasyCloset.camada_de_dominio.entidades_nao_persistidas.tratamento_excecoes.MensagensPadronizadas;
import br.com.devvader.EasyCloset.camada_de_dominio.entidades_nao_persistidas.tratamento_excecoes.RecursoNaoEncontradoException;
import br.com.devvader.EasyCloset.camada_de_dominio.entidades_nao_persistidas.tratamento_excecoes.RegraDeNegocioException;
import br.com.devvader.EasyCloset.camada_de_dominio.portas_de_servicos.IJasperService;
import br.com.devvader.EasyCloset.camada_de_recursos.repositories.IRoupaRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;
import org.springframework.stereotype.Service;
import java.io.InputStream;
import java.sql.Connection;

@Service
@AllArgsConstructor
@Slf4j
public final class JasperServiceImpl implements IJasperService {

    private IRoupaRepository iRoupaRepository;

    @Override
    public void adicionarParametrosNoMapa(String key, Object value) {
        mapaDeParametros.put(key, value);
    }

    @Override
    public void abrirJasperViewer(String jrxml) {
        JasperReport relatorioCompilado = compilarArquivoJrxml(jrxml);
        try {
            JasperPrint relatorioImpresso = JasperFillManager.fillReport(relatorioCompilado, mapaDeParametros, (Connection) iRoupaRepository);
            JasperViewer visualizadorDeRelatorio = new JasperViewer(relatorioImpresso);
            visualizadorDeRelatorio.setVisible(true);
        } catch (JRException jre) {
            log.error(jre.getMessage(), jre.getCause(), jre.getStackTrace());
            throw new RegraDeNegocioException(MensagensPadronizadas.REQUISICAO_INVALIDA);
        }
    }

    @Override
    public JasperReport compilarArquivoJrxml(String arquivo) {
        try{
            InputStream novoArquivoJrxml = getClass().getClassLoader().getResourceAsStream(arquivo);
            return JasperCompileManager.compileReport(novoArquivoJrxml);
        }catch(JRException jre) {
            log.error(jre.getMessage(), jre.getCause(), jre.getStackTrace());
            throw new RecursoNaoEncontradoException(MensagensPadronizadas.ARQUIVO_NAO_ENCONTRADO);
        }
    }
}
