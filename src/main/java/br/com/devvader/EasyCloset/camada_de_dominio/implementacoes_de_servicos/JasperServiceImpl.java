package br.com.devvader.EasyCloset.camada_de_dominio.implementacoes_de_servicos;

import br.com.devvader.EasyCloset.camada_de_dominio.entidades_nao_persistidas.tratamento_excecoes.MensagensPadronizadas;
import br.com.devvader.EasyCloset.camada_de_dominio.entidades_nao_persistidas.tratamento_excecoes.RecursoNaoEncontradoException;
import br.com.devvader.EasyCloset.camada_de_dominio.portas_de_servicos.IJasperService;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Optional;

@Service
@Slf4j
public final class JasperServiceImpl implements IJasperService {

    @Override
    public void adicionarParametrosNoMapa(String key, Object value) {
        mapaDeParametros.put(key, value);
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
