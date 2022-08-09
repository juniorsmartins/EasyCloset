package br.com.devvader.EasyCloset.camada_de_dominio.entidades_nao_persistidas.configuracoes;

import br.com.devvader.EasyCloset.camada_de_dominio.entidades_nao_persistidas.tratamento_excecoes.MensagensPadronizadas;
import br.com.devvader.EasyCloset.camada_de_dominio.entidades_nao_persistidas.tratamento_excecoes.RequisicaoInvalidaException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Configuration
public class DatabaseConfig {

    @Bean
    public Connection connection(DataSource dataSource) {
        try{
            return dataSource.getConnection();
        }catch(SQLException sqle) {
            throw new RequisicaoInvalidaException(MensagensPadronizadas.DATABASE_SEM_CONEXAO);
        }
    }
}
